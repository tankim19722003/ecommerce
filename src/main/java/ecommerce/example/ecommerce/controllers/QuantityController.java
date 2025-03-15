package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.QuantityDTOList;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.QuantityResponse;
import ecommerce.example.ecommerce.services.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/quantity")
public class QuantityController {

    @Autowired
    private QuantityService quantityService;

    @PostMapping("")
    public ResponseEntity<?> createQuantity(
            @RequestBody QuantityDTOList quantityDTOList
    ) {

        try {
            quantityService.createAttributeQuantity(quantityDTOList);
            return ResponseEntity.noContent().build();
        }   catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getAllAttributeValue(
        @PathVariable("productId") Long productId
    ) {
        try {
            List<QuantityResponse> attributeQuantity = quantityService
                    .getAllAttributeQuantityById(productId);

            return ResponseEntity.ok(attributeQuantity);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
