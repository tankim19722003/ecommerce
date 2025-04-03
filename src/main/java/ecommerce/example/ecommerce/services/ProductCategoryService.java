package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCategoryGroupDTO;
import ecommerce.example.ecommerce.dtos.MultipleProductCategoryDTO;
import ecommerce.example.ecommerce.responses.MultipleProductCategoryResponse;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryService {

//    ProductCategoryResponse addProductCategory(
//        Long productId,
//        ProductCategoryImageDTO productCategoryDTO
//    );

    List<ProductCategoryResponse> addMultipleProductCategoryOneLevel(
            Long productId,
            ProductCategoryGroupDTO productCategoryGroups,
            List<MultipartFile> files
    );

    MultipleProductCategoryResponse addMultipleProductCategoryTwoLevel(
            Long productId,
            Long shopId,
            MultipleProductCategoryDTO multipleProductCategoryResponse,
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
