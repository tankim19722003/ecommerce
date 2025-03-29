package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/create_product/{shopId}")
    private ResponseEntity<?> createProduct(
        @PathVariable("shopId") Long shopId,
        @ModelAttribute ProductCreatingDTO productCreatingDTO
    ) {

        ownerService.checkValidShop(shopId);

        try {
            productService.createProduct(shopId, productCreatingDTO);
            return ResponseEntity.noContent().build();
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
