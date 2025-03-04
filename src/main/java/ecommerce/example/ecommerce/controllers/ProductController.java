package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import ecommerce.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    private ResponseEntity<?> createProduct(
            @RequestBody ProductCreatingDTO productCreatingDTO
    ) {

        try {
            ProductCreatingResponse productCreatingResponse= productService.createProduct(productCreatingDTO);
            return ResponseEntity.ok(productCreatingResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
