package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserPrincipal;
import ecommerce.example.ecommerce.responses.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


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
        user.setPhoneNumber(userResgisterDTO.getPhoneNumber());
        user.addRole(role);

        userRepo.save(user);

    }

    @Override
    public UserLoginResponse login(UserLoginDTO userLoginDTO) {

        // get user email or phone_number
        User user = userRepo.findUserByEmailOrPhoneNumberOrAccount(userLoginDTO.getAccount()).orElseThrow(() ->
                new RuntimeException("User does not found"));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getPhoneNumber(),
                        userLoginDTO.getPassword()));

        if (authentication.isAuthenticated())
            return UserLoginResponse.builder()
                    .name("token")
                    .token(jwtService.generateToken(user.getPhoneNumber()))
                    .build();

        return null;
    }
}
