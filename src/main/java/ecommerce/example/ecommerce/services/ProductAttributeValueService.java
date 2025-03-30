package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductAttributeValueDTO;
import ecommerce.example.ecommerce.responses.ProductAttributeValueResponse;

import java.util.List;

public interface ProductAttributeValueService {

    ProductAttributeValueResponse createProductAttributeValue(
            ProductAttributeValueDTO productAttributeValueDTO,
            Long productId
    );

    List<ProductAttributeValueResponse> createMultipleProductAttributeValue(
            Long productId,
            List<ProductAttributeValueDTO> productAttributeValueDTOs
    );

    List<ProductAttributeValueResponse> getAllProductAttributeValue(Long productId);

    ProductAttributeValueResponse updateProductAttributeValue(
            ProductAttributeValueDTO productAttributeValueDTO,
            Long productAttributeValueId,
            Long productId,
            Long shopId
    );


    void deleteProductAttributeValue(
            Long productAttributeValueId,
            Long shopId
    );


}
