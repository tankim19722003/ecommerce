package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    ProductCategoryResponse addProductCategory(
        Long productId,
        ProductCategoryDTO productCategoryDTO
    );

    List<ProductCategoryResponse> addMultipleProductCategory(
            Long productId,
            List<ProductCategoryDTO> productCategoryDTOs
    );

    ProductCategoryResponse handleSaveProduct(
            ProductCategoryDTO productCategoryDTO,
            Product product
    );

    List<ProductCategoryResponse> getAllProductCategoriesByProductId(
            Long productId
    );

    ProductCategoryResponse updateProductCategory(
            ProductCategoryDTO productCategoryDTO
    );

    void deleteProductCategoryById(Long id);
}
