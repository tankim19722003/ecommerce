package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/create_product/{shopId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<?> createProduct(
        @PathVariable("shopId") Long shopId,
        @ModelAttribute ProductCreatingDTO productCreatingDTO
    ) {

        ownerService.checkValidShop(shopId);

        try {
            ProductCreatingResponse productCreatingResponse =  productService.createProduct(shopId, productCreatingDTO);
            return ResponseEntity.ok(productCreatingResponse);
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
