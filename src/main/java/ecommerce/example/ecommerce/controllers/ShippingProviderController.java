package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.UpdatingShippingProviderOrderStatusDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ShippingProviderSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/shipping_provider")
public class ShippingProviderController {

    @Autowired
    private ShippingProviderSerivice shippingProviderSerivice;

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/get_all")
    public ResponseEntity<?> ShippingProvider() {
        return ResponseEntity.ok(shippingProviderSerivice.getAllShippingProvider());
    }

    @PostMapping("/change_order_status")
    public ResponseEntity<?> changeOrderStatus(
            @RequestBody UpdatingShippingProviderOrderStatusDTO updatingShippingProviderOrderStatusDTO
    ) {
        try {
            ownerService.checkValidShippingProvider(updatingShippingProviderOrderStatusDTO.getShippingProviderId());
            shippingProviderSerivice.changeOrderStatusByShippingProvider(updatingShippingProviderOrderStatusDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.ok(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
