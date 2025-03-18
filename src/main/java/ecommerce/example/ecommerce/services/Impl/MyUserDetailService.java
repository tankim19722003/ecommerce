package ecommerce.example.ecommerce.services.Impl;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByAccount(username).orElseThrow(() ->
                new RuntimeException("User does not found"));


        if (user==null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        UserDetails userDetails = new UserPrincipal(user);
        return userDetails;
    }
}
