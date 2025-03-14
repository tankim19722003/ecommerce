package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.UserCodeDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user_code")
public class UserCodeController {

    @Autowired
    private UserCodeService userCodeService;

    @GetMapping("/send_code")
    public ResponseEntity<?> getShopCodeConConfirmation(
            @Param("userId") Long userId,
            @Param("email") String email
    )  {
        try {
            userCodeService.createAndSendCode(userId,email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );

        }
    }

    @PostMapping("/confirm_code")
    public ResponseEntity<?> confirmUserCode(
            @RequestBody UserCodeDTO userCodeDTO
    ) {

        try {
            userCodeService.confirmCode(userCodeDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            EResponse
                                    .builder()
                                    .name("ERROR")
                                    .message(e.getMessage())
                                    .build()
                    );
        }
    }

}
