package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductShippingTypeDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductShippingTypeResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/product_shipping_type")
public class ProductShippingTypeController {

    @Autowired
    private ProductShippingTypeService productShippingTypeService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("")
    public ResponseEntity<?> createProductShippingTypes(
            @RequestBody ProductShippingTypeDTO productShippingTypeDTO
    ) {
        try {

            ownerService.checkValidShop(productShippingTypeDTO.getShopId());

            ProductShippingTypeResponse productShippingTypeResponse = productShippingTypeService
                    .createProductShippingType(productShippingTypeDTO);

            return ResponseEntity.ok(productShippingTypeResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }


    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductShippingTypes(
            @PathVariable("productId") Long productId
    ) {
        try {

            ProductShippingTypeResponse productShippingTypeResponse = productShippingTypeService
                    .getAllProductShipping(productId);

            return ResponseEntity.ok(productShippingTypeResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

}
