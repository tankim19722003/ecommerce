package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.RoleRepo;
import ecommerce.example.ecommerce.Repo.UserCodeRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.CloudinaryService;
import ecommerce.example.ecommerce.dtos.UserInfoUpdating;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserRegisterDTO;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserCode;
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
import java.time.LocalDateTime;
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

    @Autowired
    private UserCodeRepo userCodeRepo;

    @Override
    @Transactional
    public void createUser(UserRegisterDTO userResgisterDTO) {

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

            // check is code active
            UserCode userCode = userCodeRepo.findUserCode(1L, userResgisterDTO.getAccount())
                    .orElseThrow(() -> new RuntimeException("Please confirm your email"));

            if (!userCode.getActive()) throw new RuntimeException("Please active your code");

            if (!userCode.getEmail().equals(userResgisterDTO.getAccount())) {
                throw new RuntimeException("Invalid code");
            }

            user.setEmail(userResgisterDTO.getAccount());
        } else {
            throw new RuntimeException("Invalid phone number or password!!");
        }


        userRepo.save(user);

        userCodeRepo.deleteByEmail(userResgisterDTO.getAccount());

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
                .authenticate(new UsernamePasswordAuthenticationToken(user.getAccount(),
                        userLoginDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            UserLoginResponse userLoginResponse = new UserLoginResponse();

            userLoginResponse.setUserResponse(user.toUserResponse());
            userLoginResponse.setToken(jwtService.generateToken(user.getAccount()));

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
    public UserResponse getUserInfo(Long id) {

        User user = userRepo.findById(id).orElseThrow(() ->
                new RuntimeException("User does not found"));

        List<Role> roles = roleRepo.findAllByUserId(user.getId());

        UserResponse userResponse = user.toUserResponse();

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse updateUserInfo(UserInfoUpdating userInfoUpdating, long userId) {

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


        // check phone number is update
        String token = null;
        if (!userInfoUpdating.getAccount().equals(user.getAccount())) {
            token = jwtService.generateToken(userInfoUpdating.getAccount());
        }
        // save user
        modelMapper.map(userInfoUpdating, user);
        User userSaved = userRepo.save(user);


        // create Response
        if (token != null) {
            UserUpdatedResponse userUpdatedResponse = user.toUserUpdatedResponse();
            userUpdatedResponse.setToken(token);

            return userUpdatedResponse;
        }

        return user.toUserResponse();
    }

    @Override
    @Transactional
    public ImageResponse updateAvatar(MultipartFile file, Long userId) throws IOException {

        User user = userRepo.findById(userId).orElseThrow(
                () ->  new RuntimeException("User does not found")
        );

        if (user.getPublicId() != null) {
            cloudinaryService.deleteImage(user.getPublicId());
        }

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

    @Override
    @Transactional
    public UserResponse updateEmail(Long userId, String email) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User does not found")
        );


        boolean isValidEmail = isEmail(email);
        if (!isValidEmail) {
            throw new RuntimeException("Invalid email");
        }

        UserCode userCode = userCodeRepo
                .findUserCode(1L, email)
                .orElseThrow(() -> new RuntimeException("Invalid Code"));

        if (!userCode.getActive()) {
            throw new RuntimeException("Please active your email!!");
        }
        if (userCode.getUser().getId() != userId)
                throw new RuntimeException("Invalid code!");

        if (userCode.getDateEnd().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code is expired !! request new one!!");
        }

        user.setEmail(email);
        userRepo.save(user);

        userCodeRepo.deleteByUserId(userId);

        return user.toUserResponse();
    }


}
