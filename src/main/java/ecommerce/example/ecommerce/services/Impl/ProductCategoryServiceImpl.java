package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductCategoryGroupRepo;
import ecommerce.example.ecommerce.Repo.ProductCategoryRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryGroupDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductCategory;
import ecommerce.example.ecommerce.models.ProductCategoryGroup;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import ecommerce.example.ecommerce.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private ProductCategoryGroupRepo productCategoryGroupRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ProductCategoryResponse> addMultipleProductCategory(
            Long productId,
            List<ProductCategoryGroupDTO> productCategoryGroups,
            List<MultipartFile> files
    ) {

        Product product = productRepo.findById(productId).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );

        // check image did images add into product category
        int count = 0;
        for (ProductCategoryGroupDTO productCategoryGroupDTO : productCategoryGroups) {
            count += productCategoryGroupDTO.getProductCategoryDTOs().size();
        }
        if (files.size() != count) throw new RuntimeException("Please add image to your product categories!");

        List<Map<String, String>> images = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                images.add(cloudinaryService.uploadImage(file));
            } catch (IOException e) {
                throw new RuntimeException("Can't save image");
            }
        }

        List<ProductCategoryResponse> productCategoryResponseList = new ArrayList<>();

        int index = 0;
        for (ProductCategoryGroupDTO productCategoryGroupItem : productCategoryGroups) {

            // save product category group
            ProductCategoryGroup productCategoryGroup = new ProductCategoryGroup();
            productCategoryGroup.setName(productCategoryGroupItem.getName());
            productCategoryGroup.setProduct(product);
            productCategoryGroupRepo.save(productCategoryGroup);


            for (ProductCategoryDTO productCategoryDTO : productCategoryGroupItem.getProductCategoryDTOs()) {

                // save product category
                ProductCategory productCategory = new ProductCategory();

                // map first and second category
                modelMapper.map(productCategoryDTO, productCategory);

                productCategory.setImageUrl(images.get(index).get("publicId"));
                productCategory.setPublicId(images.get(index).get("imageUrl"));
                productCategory.setProductCategoryGroup(productCategoryGroup);
                productCategory.setPrice(productCategoryDTO.getPrice());

                // save product category
                productCategoryRepo.save(productCategory);
                index++;

                // add product category to response
                productCategoryResponseList.add(modelMapper.map(productCategory, ProductCategoryResponse.class));
            }
        }

        return productCategoryResponseList;

    }

//    @Override
//    public ProductCategoryResponse handleSaveProduct(ProductCategoryImageDTO productCategoryDTO, Product product) {
//        Map<String, String> cloudImage;
//        // save product category image
//        try {
//            cloudImage = cloudinaryService.uploadImage(productCategoryDTO.getImage());
//        } catch (Exception e) {
//            throw new RuntimeException("Can't save image");
//        }
//
//        ProductCategory productCategory = ProductCategory.builder()
//                .firstCategory(productCategoryDTO.getFirstCategory())
//                .secondCategory(productCategoryDTO.getSecondCategory())
//                .quantity(productCategoryDTO.getQuantity())
//                .imageUrl(cloudImage.get("imageUrl"))
//                .publicId(cloudImage.get("publicId"))
//                .product(product)
//                .build();
//
//        try {
//            productCategoryRepo.save(productCategory);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to save product category!!");
//        }
//
//        return modelMapper.map(productCategory, ProductCategoryResponse.class);
//
//    }
//
//
//    @Override
//    public List<ProductCategoryResponse> getAllProductCategoriesByProductId(Long productId) {
//
//        List<ProductCategory> productCategories = productCategoryRepo.findByProductId(productId);
//        return productCategories
//                .stream()
//                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryResponse.class))
//                .toList();
//    }
//
//    @Override
//    public ProductCategoryResponse updateProductCategory(Long productCategoryId, ProductCategoryDTO productCategoryDTO) {
//
//        ProductCategory productCategory = productCategoryRepo.findById(productCategoryId)
//                .orElseThrow(() -> new RuntimeException("Product category does not exist!!"));
//
//        modelMapper.map(productCategoryDTO, productCategory);
//
//        return modelMapper.map(productCategory, ProductCategoryResponse.class);
//    }
//
//    @Override
//    @Transactional
//    public void deleteProductCategoryById(Long id) {
//        ProductCategory productCategory = productCategoryRepo.findById(id)
//                        .orElseThrow(() -> new RuntimeException("Product category does not found!!"));
//
//        productCategoryRepo.delete(productCategory);
//
//        // delete image on cloud
//        cloudinaryService.deleteImage(productCategory.getPublicId());
//
//    }
}
