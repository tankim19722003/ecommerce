package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.models.Role;
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

    public void checkValidShop(Long shopId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        User user = userRepo.findByAccount(userPrincipal.getUsername()).orElseThrow(
                () -> new RuntimeException("User does not found")
        );

        if (user.getShop() == null)
            throw new RuntimeException("Shop does not found");

        if (user.getShop().getId() != shopId)  throw new SecurityException("You are not allowed to modify this user's data!");


    }

    public void checkValidShippingProvider(Long shippingProviderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        User user = userRepo.findByAccount(userPrincipal.getUsername()).orElseThrow(
                () -> new RuntimeException("User does not found")
        );

        // admin permit all
//        for (Role role : user.getRoles()) {
//            if (role.getName().toUpperCase().equals(Role.ADMIN)) return;
//        }

        if (user.getShippingProvider() == null)
            throw new RuntimeException("Can't find shipping provider!!");

        if (user.getShippingProvider().getId() != shippingProviderId)
            throw new SecurityException("You are not allowed to modify this user's data!");

    }
}
