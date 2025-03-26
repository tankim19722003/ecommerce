package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryImageDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryResponse addProductCategory(
        Long productId,
        ProductCategoryImageDTO productCategoryDTO
    );

    List<ProductCategoryResponse> addMultipleProductCategory(
            Long productId,
            List<ProductCategoryImageDTO> productCategoryDTOs
    );

    ProductCategoryResponse handleSaveProduct(
            ProductCategoryImageDTO productCategoryDTO,
            Product product
    );

    List<ProductCategoryResponse> getAllProductCategoriesByProductId(
            Long productId
    );

    ProductCategoryResponse updateProductCategory(
            Long productCategoryId,
            ProductCategoryDTO productCategoryDTO
    );

    void deleteProductCategoryById(Long id);
}
