package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void createUser(UserResgisterDTO userResgisterDTO) {

        Role role = roleRepo.findByName("user").orElseThrow(() -> new RuntimeException("Role does not found"));

        User user = new User();
        user.setAvatar("user.png");
        user.setAccount("user" + System.currentTimeMillis());
        user.setEmail(userResgisterDTO.getEmail());
        user.setPassword(encoder.encode(userResgisterDTO.getPassword()));
        user.setPhoneNumber(user.getPhoneNumber());
        user.addRole(role);

    }
}
