package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.QuantityDTOList;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
