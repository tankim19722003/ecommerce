package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductShippingInfoDTO;
import ecommerce.example.ecommerce.dtos.ShippingFeeDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ShippingFeeResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_shipping")
public class ProductShippingController {

    @Autowired
    private ProductShippingService productShippingService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/calculate_each_shipping_type_fee")
    public List<ShippingFeeResponse> calculateEachShippingTypeFee(
            @RequestBody ShippingFeeDTO shippingFeeDTO
    ) {
        return productShippingService.calculateShippingFee(shippingFeeDTO);
    }


    public ResponseEntity<?> createProductShippingType(
            @RequestBody ProductShippingInfoDTO productShippingInfoDTO
    ) {

        try {
            ownerService.checkValidShop(productShippingInfoDTO.getShopId());
            productShippingService.createProductShippingInfo(productShippingInfoDTO);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
