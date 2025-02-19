package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserInfoUpdating;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserPrincipal;
import ecommerce.example.ecommerce.responses.UserLoginResponse;
import ecommerce.example.ecommerce.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

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

    @Autowired
    private ModelMapper modelMapper;

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

        // check valid password
        if (!encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Invalid user");
        }

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

    @Override
    public UserResponse getUserInfo(String token) {
        String phoneNumber = jwtService.extractUserName(token);

        User user = userRepo.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new RuntimeException("User does not found"));

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId) {

        User user = userRepo.findById(userId).orElseThrow(() ->
                new RuntimeException("User does not found"));

        // check account existing
        Boolean isAccountExisting = userRepo.existsByAccount(userInfoUpdating.getAccount());
        if (isAccountExisting)
            throw new RuntimeException("Account is existing!!");


        // check email existing
        Boolean isEmailExisting = userRepo.existsByEmail(userInfoUpdating.getEmail());
        if (isEmailExisting) throw new RuntimeException("Email is existing!!");

        // check phone number existing
        Boolean isPhoneNumberExisting = userRepo.existsByPhoneNumber(userInfoUpdating.getPhoneNumber());
        if (isPhoneNumberExisting) throw  new RuntimeException("Phone number is existing!!");


        // convert User updating to user
        modelMapper.map(userInfoUpdating, user);

        User userSaved = userRepo.save(user);

        return modelMapper.map(userSaved, UserResponse.class);
    }


}
