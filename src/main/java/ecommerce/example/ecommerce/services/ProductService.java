package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.responses.*;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    ProductCreatingResponse createProduct(Long shopId, ProductCreatingDTO productCreatingDTO);

    QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId);

    void getProductById(Long productId);

    List<ProductKeywordResponse> getProductsByKeyWord(String keyword);

    List<ProductRatingOrderResponse> getProductsWithRatingOrder(PageRequest pageRequest);
}
