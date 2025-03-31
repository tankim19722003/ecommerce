package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.ShippingTypeResponse;

import java.util.List;

public interface ShippingTypeService {
    List<ShippingTypeResponse> getAllShippingType();

//    ShippingTypeResponse createShippingType(ShippingTypeDTO shippingTypeDTO);
//
//    ShippingTypeResponse updateShippingType(Long shippingTypeId,ShippingTypeDTO shippingTypeDTO);
//
//    void deleteShippingType(Long shippingTypeId);
}
