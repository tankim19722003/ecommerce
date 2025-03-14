package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.AttributeValueResponse;

import java.util.List;

public interface ProductAttributeValueService {

    List<AttributeValueResponse> getAllProductAttributeValuesByProductId(Long productId);

}
