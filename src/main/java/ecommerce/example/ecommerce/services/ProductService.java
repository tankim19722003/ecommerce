package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.responses.*;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    ProductCreatingResponse createProduct(Long shopId, ProductCreatingDTO productCreatingDTO);

    QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId);


    ProductKeywordPageResponse getProductsByKeyWord(PageRequest pageRequest, String keyword);

    ProductRatingOrderPageResponse getProductsWithRatingOrder(PageRequest pageRequest);

    ProductDetailResponse getProductDetails(Long productId);

    List<ProductDetailResponse> getProductDetailsByShopId(Long shopId);

}
