package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductShippingTypeDTO;
import ecommerce.example.ecommerce.responses.ProductShippingTypeResponse;

public interface ProductShippingTypeService {

    ProductShippingTypeResponse createProductShippingType(
            ProductShippingTypeDTO productShippingTypeDTO
    );

    ProductShippingTypeResponse getAllProductShipping(
            Long productId
    );

}
