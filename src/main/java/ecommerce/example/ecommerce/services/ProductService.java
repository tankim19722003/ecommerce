package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.responses.QuantityResponse;

import java.util.List;

public interface ProductService {

    void createProduct(Long shopId, ProductCreatingDTO productCreatingDTO);

    QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId);

    void getProductById(Long productId);
}
