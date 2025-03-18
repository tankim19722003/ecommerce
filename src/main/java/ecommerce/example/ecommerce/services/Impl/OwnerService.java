package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private UserRepo userRepo ;

    public void checkValidUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        User user = userRepo.findByAccount(userPrincipal.getUsername()).orElseThrow(
                () -> new RuntimeException("User does not found")
        );

        if (user.getId() != userId)  throw new SecurityException("You are not allowed to modify this user's data!");


    }
}
