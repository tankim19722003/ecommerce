package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.ProductAttributeValueDTO;
import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.AttributeValueResponse;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import ecommerce.example.ecommerce.responses.QuantityResponse;
import ecommerce.example.ecommerce.services.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubCategoryRepo categoryRepo;

    @Autowired
    private ShopRepo shopRepo;

//    @Autowired
//    private ProductAttributeValueRepo productAttributeValueRepo;

    @Autowired
    public CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Override
    public void getProductById(Long productId) {

        

    }

    @Override
    @Transactional
    public void createProduct(Long shopId, ProductCreatingDTO productCreatingDTO) {

        SubCategory category = categoryRepo.findById(productCreatingDTO.getSubcategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

        Shop shop = shopRepo.findById(shopId).orElseThrow(
                () ->  new RuntimeException("Shop does not found")
        );


        // save the avatar to the cloud
        Map<String, String> cloudAvatar;
        try {
             cloudAvatar = cloudinaryService.uploadImage(productCreatingDTO.getThumbnail());
        } catch (IOException e) {
            throw new RuntimeException("Can't save avatar to the cloud");
        }


        Product product = Product.builder()
                .name(productCreatingDTO.getName())
                .description(productCreatingDTO.getDescription())
                .subCategory(category)
                .thumbnailUrl(cloudAvatar.get("imageUrl"))
                .thumbnailPublicId(cloudAvatar.get("publicId"))
                .shop(shop)
                .build();

        // save product
        productRepo.save(product);

        Product savedProduct = productRepo.save(product);


        // save the product images
        for (MultipartFile image : productCreatingDTO.getProductImages()) {
            Map<String, String> cloudImage;
            try {
                cloudImage = cloudinaryService.uploadImage(image);
            } catch (IOException e) {
                throw new RuntimeException("Can't save the product image");
            }

            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setUrl(cloudImage.get("imageUrl"));
            productImage.setPublicId(cloudImage.get("publicId"));

            try {
                productImageRepo.save(productImage);
            } catch (Exception e) {
                throw new RuntimeException("Can't save product image to db");
            }

        }

    }

    @Override
    public QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId) {
        return null;
    }
}
