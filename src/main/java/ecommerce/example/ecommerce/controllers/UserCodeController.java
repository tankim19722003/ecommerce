package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.UserCodeDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
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

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/user/send_code")
    public ResponseEntity<?> getUserCodeConfirmation(
            @Param("userId") Long userId,
            @Param("email") String email
    )  {
        try {
            ownerService.checkValidUser(userId);
            userCodeService.handleCode(userId,email, 1L);
            return ResponseEntity.ok("");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );

        }
    }

    @GetMapping("/send_code")
    public ResponseEntity<?> getUserCodeConfirmation(
            @Param("email") String email
    )  {
        try {
            userCodeService.handleCode(email, 1L);
            return ResponseEntity.ok("");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );

        }
    }




    @PostMapping("/user/confirm_code/{userId}")
    public ResponseEntity<?> confirmUserEmailCode(
            @RequestBody UserCodeDTO userCodeDTO,
            @PathVariable("userId") long userId
    ) {

        try {
            ownerService.checkValidUser(userId);
            userCodeService.confirmCode(userCodeDTO, userId);
            return ResponseEntity.ok("");
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

    @PostMapping("/confirm_code")
    public ResponseEntity<?> confirmUserEmailCode(
            @RequestBody UserCodeDTO userCodeDTO
    ) {

        try {
            userCodeService.confirmCode(userCodeDTO);
            return ResponseEntity.ok("");
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
