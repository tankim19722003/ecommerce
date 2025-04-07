package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.models.ProductImage;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ImageResponse;
import ecommerce.example.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_image")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductImages(
        @PathVariable("productId") Long productId
    ) {

        try {
            List<ImageResponse> productImages = productImageService.getProductImages(productId);
            return ResponseEntity.ok(productImages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }
}
