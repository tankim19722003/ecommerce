package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductAttributeValueDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductAttributeValueResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_attribute_value")
public class ProductAttributeValueController {

    @Autowired
    private ProductAttributeValueService productAttributeValueService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getAllProductAttributeValues(
            @PathVariable ("productId") long productId
    ) {
        try {
            List <ProductAttributeValueResponse> productAttributeValueResponses = productAttributeValueService
                    .getAllProductAttributeValue(productId);

            return ResponseEntity.ok(productAttributeValueResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/add_one")
    public ResponseEntity<?> createProductAttributeValue(
            @RequestParam("productId") Long productId,
            @RequestParam("shopId") Long shopId,
            @RequestBody ProductAttributeValueDTO productAttributeValueDTO
    ) {

        try {
            ownerService.checkValidShop(shopId);
            ProductAttributeValueResponse productAttributeValueResponse = productAttributeValueService
                    .createProductAttributeValue(productAttributeValueDTO,productId);
            return ResponseEntity.ok(productAttributeValueResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/add_multiple")
    public ResponseEntity<?> createProductAttributeValues(
            @RequestParam("productId") Long productId,
            @RequestParam("shopId") Long shopId,
            @RequestBody List<ProductAttributeValueDTO> productAttributeValueDTOs
    ) {

        try {
            ownerService.checkValidShop(shopId);
            List<ProductAttributeValueResponse> productAttributeValueResponse = productAttributeValueService
                    .createMultipleProductAttributeValue(productId, productAttributeValueDTOs);
            return ResponseEntity.ok(productAttributeValueResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateProductAttributeValue(
            @RequestParam("productId") Long productId,
            @RequestParam("shopId") Long shopId,
            @RequestParam("productAttributeValueId") Long productAttributeValueId,
            @RequestBody ProductAttributeValueDTO productAttributeValueDTO
    ) {

        try {
            ownerService.checkValidShop(shopId);
            ProductAttributeValueResponse productAttributeValueResponse = productAttributeValueService
                    .updateProductAttributeValue(productAttributeValueDTO, productAttributeValueId, productId, shopId);
            return ResponseEntity.ok(productAttributeValueResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> updateProductAttributeValue(
            @RequestParam("productAttributeValueId") Long productAttributeValueId,
            @RequestParam("shopId") Long shopId
    ) {

        try {
            ownerService.checkValidShop(shopId);

            productAttributeValueService
                    .deleteProductAttributeValue(productAttributeValueId, shopId);

            return ResponseEntity.noContent().build();
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
