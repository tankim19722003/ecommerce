package ecommerce.example.ecommerce.controllers;


import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.responses.CompletingOrderResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.OrderResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping()
    public ResponseEntity<?> createOrder(
            @RequestBody OrderDTO orderDTO
    ) {

        try {
            ownerService.checkValidUser(orderDTO.getUserId());
            OrderResponse orderResponse = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(orderResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/get_order_by_user_id_and_status")
    public ResponseEntity<?> getOrderByUserIdAndStatus(
        @RequestParam("userId") Long userId,
        @RequestParam("status") String status

    ) {

        try {
            ownerService.checkValidUser(userId);
            List<CompletingOrderResponse> orderResponses = orderService.getCompletingOrder(userId, status);
            orderService.getCompletingOrder(userId, status);
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/cancel_order")
    public ResponseEntity<?> cancelOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidUser(userId);
            orderService.cancelOrder(userId, orderId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

}
