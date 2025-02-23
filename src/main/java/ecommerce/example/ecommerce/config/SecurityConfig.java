package ecommerce.example.ecommerce.config;

import ecommerce.example.ecommerce.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public AuthenticationProvider authProvider() {
        // work with db
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> {
                    request
                            // for user
                            .requestMatchers(POST,
                                    String.format("%s/user/login/**", apiPrefix)).permitAll()

                            .requestMatchers(PUT,
                                    String.format("%s/user/register/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/user/get_user_info/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/user/update_user_info/**", apiPrefix)).hasRole(Role.USER)

                            // for address
                            .requestMatchers(POST,
                                    String.format("%s/user_village/add_user_address", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/user_village/update_user_address/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(PUT,
                                    String.format("%s/user_village/get_all_address/**", apiPrefix)).hasRole(Role.USER)

                            .requestMatchers(GET,
                                    String.format("%s/user_village/get_all_province", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN, Role.SHOP)
                            .anyRequest().permitAll();
                    }

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);




        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization","content-type","x-auth-token","Accept-Languague"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**",configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
