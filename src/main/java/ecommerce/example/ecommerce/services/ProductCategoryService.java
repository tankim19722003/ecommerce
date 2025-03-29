package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryGroupDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryImageDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductCategoryGroup;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryService {

//    ProductCategoryResponse addProductCategory(
//        Long productId,
//        ProductCategoryImageDTO productCategoryDTO
//    );

    void addMultipleProductCategory(
            Long productId,
            List<ProductCategoryGroupDTO> productCategoryGroups,
            List<MultipartFile> files
    );
//
//    ProductCategoryResponse handleSaveProduct(
//            ProductCategoryImageDTO productCategoryDTO,
//            Product product
//    );
//
//    List<ProductCategoryResponse> getAllProductCategoriesByProductId(
//            Long productId
//    );
//
//    ProductCategoryResponse updateProductCategory(
//            Long productCategoryId,
//            ProductCategoryDTO productCategoryDTO
//    );
//
//    void deleteProductCategoryById(Long id);
}
