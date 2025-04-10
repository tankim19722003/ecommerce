package ecommerce.example.ecommerce.controllers;


import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.OrderResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createOrder(
            @RequestBody OrderDTO orderDTO,
            @PathVariable("userId") Long userId
    ) {

        try {

            ownerService.checkValidUser(userId);
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
}
