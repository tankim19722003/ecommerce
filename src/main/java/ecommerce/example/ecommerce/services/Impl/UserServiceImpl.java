package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Exceptions.ValidationException;
import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserInfoUpdating;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void createUser(UserResgisterDTO userResgisterDTO) {

        Role role = roleRepo.findByName("user").orElseThrow(() -> new RuntimeException("Role does not found"));

//        check is phone  number
        boolean isPhoneNumber = isPhoneNumber(userResgisterDTO.getAccount());
        boolean isEmail = isEmail(userResgisterDTO.getAccount());

        User user = new User();
        user.setAccount("user" + System.currentTimeMillis());
        user.setPassword(encoder.encode(userResgisterDTO.getPassword()));
        user.addRole(role);

        if (isPhoneNumber) {
            //        check existing phoneNumber
            Boolean isPhoneNumberExisting = userRepo.existsByPhoneNumber(userResgisterDTO.getAccount());
            if (isPhoneNumberExisting) throw new RuntimeException("Phone number is existing!!");

            user.setPhoneNumber(userResgisterDTO.getAccount());
        } else if(isEmail) {

            //        check existing phoneNumber
            Boolean isEmailExisting = userRepo.existsByEmail(userResgisterDTO.getAccount());
            if (isEmailExisting) throw new RuntimeException("Email is existing!!");

            user.setEmail(userResgisterDTO.getAccount());
        } else {
            throw new RuntimeException("Invalid phone number or password!!");
        }


        userRepo.save(user);

    }

    @Override
    public UserLoginResponse login(UserLoginDTO userLoginDTO) {

        // get user email or phone_number
        User user = userRepo.findUserByPhoneNumberOrAccountOrEmail(userLoginDTO.getAccount()).orElseThrow(() ->
                new RuntimeException("Failed to login"));

        List<Role> roles = roleRepo.findAllByUserId(user.getId());
        // check valid password
        if (!encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Failed to login");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getPhoneNumber(),
                        userLoginDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            UserLoginResponse userLoginResponse = new UserLoginResponse();

            userLoginResponse.setToken(jwtService.generateToken(user.getPhoneNumber()));

            for (Role role : roles) {
                userLoginResponse.addRole(role.getName());
            }

            return userLoginResponse;
        }


        return null;
    }


    private boolean isPhoneNumber(String phoneNumber) {
        // Regex for Vietnam phone numbers
        String regex = "^(\\+84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$";

        // Check if the phone number matches the regex
        return Pattern.matches(regex, phoneNumber);
    }

    private boolean isEmail(String email) {

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        // Check if the phone number matches the regex
        return Pattern.matches(regex, email);

    }


    @Override
    public UserResponse getUserInfo(String token) {
        String phoneNumber = jwtService.extractUserName(token);

        User user = userRepo.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new RuntimeException("User does not found"));

        List<Role> roles = roleRepo.findAllByUserId(user.getId());

        UserResponse userResponse = user.toUserResponse();

        for (Role role : roles) {
            userResponse.addRole(role.getName());
        }

        return userResponse;
    }

    @Override
    @Transactional
    public IUserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId) {

        List<EResponse> errors = new ArrayList<>();
        User user = userRepo.findById(userId).orElseThrow(() ->
                new RuntimeException("User does not found"));

        // check account existing
        Boolean isAccountExisting = userRepo
                .existsByAccountAndDifferentUserId(userInfoUpdating.getAccount(), userId);

        if (isAccountExisting) errors.add(
                EResponse.builder()
                        .name("account")
                        .message("Account is existing")
                        .build()
        );


        // check email existing
//        Boolean isEmailExisting = userRepo
//                .existsByEmailAndDifferentUserId(userInfoUpdating.getEmail(), userId);
//        if (isEmailExisting) errors.add(
//                EResponse.builder()
//                        .name("email")
//                        .message("Email is existing!!")
//                        .build()
//        );
//            throw new RuntimeException("Email is existing!!");

        // check phone number existing
//        Boolean isPhoneNumberExisting = userRepo
//                .existsByPhoneNumberAndDifferentUserId(userInfoUpdating.getPhoneNumber(), userId);

//        if (isPhoneNumberExisting) errors.add(
//                EResponse.builder()
//                        .name("phone_number")
//                        .message("Phone number is existing!!")
//                        .build()
//        );

        if (!errors.isEmpty()) throw new ValidationException(errors);


        // check phone number is update
        String token = null;
//        if (!userInfoUpdating.getPhoneNumber().equals(user.getPhoneNumber())) {
//            token = jwtService.generateToken(userInfoUpdating.getPhoneNumber());
//        }
        // save user
        modelMapper.map(userInfoUpdating, user);
        User userSaved = userRepo.save(user);


        // create Response
        if (token != null) {
            UserUpdatedResponse userUpdatedResponse =  modelMapper.map(userSaved, UserUpdatedResponse.class);
            userUpdatedResponse.setToken(token);

            return userUpdatedResponse;
        } else {
            return modelMapper.map(userSaved, UserResponse.class);
        }

    }

    @Override
    @Transactional
    public ImageResponse updateAvatar(MultipartFile file, Long userId) throws IOException {

        User user = userRepo.findById(userId).orElseThrow(
                () ->  new RuntimeException("User does not found")
        );

        Map<String, String> image = cloudinaryService.uploadImage(file);

        user.setAvatarUrl(image.get("imageUrl"));
        user.setPublicId(image.get("publicId"));

        userRepo.save(user);

        return ImageResponse
                .builder()
                .avatarUrl(user.getAvatarUrl())
                .publicId(user.getPublicId())
                .build();
    }


}
