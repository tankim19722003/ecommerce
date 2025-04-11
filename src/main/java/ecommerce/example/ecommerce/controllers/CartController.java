package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.CartDTO;
import ecommerce.example.ecommerce.dtos.CartItemUpdatingDTO;
import ecommerce.example.ecommerce.responses.CartItemResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.CartService;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add_product_to_cart")
    public ResponseEntity<?> addItemToCart(
            @RequestBody CartDTO cartDTO
    ) {

        try {
            ownerService.checkValidUser(cartDTO.getUserId());
            cartService.addProductToCart(cartDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body( EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllCartItemByProductId(
        @PathVariable("userId") Long userId
    ) {

        try {
            ownerService.checkValidUser(userId);
            List<CartItemResponse> cartItemResponses = cartService.getAllCartItems(userId);
            return ResponseEntity.ok(cartItemResponses);
        } catch(Exception e) {
            return ResponseEntity.badRequest()
                    .body( EResponse.builder()
                    .name("ERROR")
                    .message(e.getMessage())
                    .build());
        }

    }
}
