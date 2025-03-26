package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductCategoryRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductCategory;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import ecommerce.example.ecommerce.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductCategoryResponse addProductCategory(Long productId, ProductCategoryDTO productCategoryDTO) {

        Product product = productRepo.findById(productId).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );

       return handleSaveProduct(productCategoryDTO, product);
    }

    @Override
    public List<ProductCategoryResponse> addMultipleProductCategory(
            Long productId,
            List<ProductCategoryDTO> productCategoryDTOs
    ) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );

        List<ProductCategoryResponse> productCategoryResponses = new ArrayList<>();
        for (ProductCategoryDTO productCategoryDTO : productCategoryDTOs) {
            productCategoryResponses.add(handleSaveProduct(productCategoryDTO, product));
        }

        return productCategoryResponses;
    }

    @Override
    public ProductCategoryResponse handleSaveProduct(ProductCategoryDTO productCategoryDTO, Product product) {
        Map<String, String> cloudImage;
        // save product category image
        try {
            cloudImage = cloudinaryService.uploadImage(productCategoryDTO.getImage());
        } catch (Exception e) {
            throw new RuntimeException("Can't save image");
        }

        ProductCategory productCategory = ProductCategory.builder()
                .firstCategory(productCategoryDTO.getFirstCategory())
                .secondCategory(productCategoryDTO.getSecondCategory())
                .quantity(productCategoryDTO.getQuantity())
                .imageUrl(cloudImage.get("imageUrl"))
                .publicId(cloudImage.get("publicId"))
                .product(product)
                .build();

        productCategoryRepo.save(productCategory);

        return modelMapper.map(productCategory, ProductCategoryResponse.class);

    }


    @Override
    public List<ProductCategoryResponse> getAllProductCategoriesByProductId(Long productId) {

        List<ProductCategory> productCategoryResponses = productCategoryRepo.findByProductId(productId);
        return null;
    }

    @Override
    public ProductCategoryResponse updateProductCategory(ProductCategoryDTO productCategoryDTO) {
        return null;
    }

    @Override
    public void deleteProductCategoryById(Long id) {

    }
}
