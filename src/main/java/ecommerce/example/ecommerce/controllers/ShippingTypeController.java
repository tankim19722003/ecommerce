package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.services.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/shipping_type")
public class ShippingTypeController {

    @Autowired
    private ShippingTypeService shippingTypeService;

    @GetMapping("")
    public List<ShippingTypeResponse> getAllShippingTypes() {
        return shippingTypeService.getAllShippingType();
    }
}
