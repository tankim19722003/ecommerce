package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.QuantityDTOList;
import ecommerce.example.ecommerce.responses.QuantityResponse;

import java.util.List;

public interface QuantityService {
//    List<QuantityResponse> getAllQuantityAttributeByProductId(Long productId);
    void createAttributeQuantity(QuantityDTOList quantityDTOList) throws Exception;
    List<QuantityResponse> getAllAttributeQuantityById(Long productId);
}
