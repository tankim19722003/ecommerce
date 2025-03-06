package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductImageResponse;
import ecommerce.example.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("${api.prefix}/image")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @PostMapping(value = "/product_image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId,
            @RequestParam("productAttributeValueId") Long productAttributeValueId
    ) throws IOException {
        try {
            ProductImageResponse productImageResponse =  productImageService.addImageToProduct(file, productId, productAttributeValueId);
            return ResponseEntity.ok(productImageResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
