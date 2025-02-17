package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.UserResgisterDTO;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserResgisterDTO userResgisterDTO,
            BindingResult result
    ) {

        try {
            if (result.hasErrors()) {
                List<EResponse> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(fieldError -> EResponse.builder()
                                    .name(fieldError.getField())
                                    .message(fieldError.getDefaultMessage())
                                    .build()
                        ).collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }

            return ResponseEntity.ok().body(EResponse.builder()
                            .name("Successful")
                            .message("Create User successfully!!")
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
