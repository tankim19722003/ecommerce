package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.FeedBackDTO;
import ecommerce.example.ecommerce.dtos.FeedBackUpdating;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.FeedBackResponse;
import ecommerce.example.ecommerce.services.FeedbackService;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/create_feedback/{userId}")
    public ResponseEntity<?> createFeedback(
            @PathVariable("userId") Long userId,
            @RequestBody FeedBackDTO feedBackDTO
    ) {

        try {
            ownerService.checkValidUser(userId);
            FeedBackResponse feedBackResponse =  feedbackService.createFeedBack(feedBackDTO);
            return ResponseEntity.ok(feedBackResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PutMapping("/update_feedback/{userId}")
    public ResponseEntity<?> updateFeedBack(
        @PathVariable("userId") long userId,
        @RequestBody FeedBackUpdating feedBackUpdating
    ) {

        try {
            ownerService.checkValidUser(userId);
            FeedBackResponse feedBackResponse =  feedbackService.updateFeedBack(feedBackUpdating);
            return ResponseEntity.ok(feedBackResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @GetMapping("/get_all_feed_back/{productId}")
    public ResponseEntity<?> getAllFeedBackByProductId(
            @PathVariable("productId") Long productId
    ) {

        try {
            List<FeedBackResponse> feedBackResponse =  feedbackService.getAllFeedBackByProductId(productId);
            return ResponseEntity.ok(feedBackResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

}
