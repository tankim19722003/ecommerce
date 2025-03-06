package ecommerce.example.ecommerce.config;

import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.services.Impl.JwtService;
import ecommerce.example.ecommerce.services.Impl.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isByPassToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;
        String username = null;
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
            return;
        }

        token = header.substring(7);
        username = jwtService.extractUserName(token);

        Boolean isPhoneExisting = userRepo.existsByPhoneNumber(username);
        if (!isPhoneExisting) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
            return;
        }

        // haven't authenticate with spring security
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);
    }

    public boolean isByPassToken(HttpServletRequest request) {
        List<String> requests = new ArrayList<>();
        requests.add("/user/register");
        requests.add("/user/login");

        for (String requestItem : requests ) {
            if (request.getRequestURI().equals(apiPrefix + requestItem)) return true;
        }

        return false;

    }
}
