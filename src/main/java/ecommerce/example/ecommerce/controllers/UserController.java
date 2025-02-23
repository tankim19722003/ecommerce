package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.Exceptions.ValidationException;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserInfoUpdating;
import ecommerce.example.ecommerce.dtos.UserLoginDTO;
import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.IUserResponse;
import ecommerce.example.ecommerce.responses.UserLoginResponse;
import ecommerce.example.ecommerce.responses.UserResponse;
import ecommerce.example.ecommerce.services.UserService;
import ecommerce.example.ecommerce.services.ValidataDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserResgisterDTO userResgisterDTO,
            BindingResult result
    ) {

        try {

            userService.createUser(userResgisterDTO);

            return ResponseEntity.ok().body(EResponse.builder()
                            .name("register")
                            .message("Create User successfully!!")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO,
            BindingResult result
    ) {

        try {
            UserLoginResponse userLoginResponse = userService.login(userLoginDTO);

            return ResponseEntity.ok().body(userLoginResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }


    @GetMapping("get_user_info/{token}")
    public ResponseEntity<?> getUserInfo(
            @PathVariable("token") String token
    ) {

        try {
            UserResponse userResponse = userService.getUserInfo(token);
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @PutMapping("/update_user_info/{userId}")
    public ResponseEntity<?> updateUserInfo(
            @RequestBody UserInfoUpdating userInfoUpdating,
            @PathVariable("userId") long userId
    ) {
        try {
            IUserResponse userResponse =  userService.updateUserInfo(userInfoUpdating, userId);
            return ResponseEntity.ok(userResponse);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(
                    e.getErrors()
            );
        }

    }

}
