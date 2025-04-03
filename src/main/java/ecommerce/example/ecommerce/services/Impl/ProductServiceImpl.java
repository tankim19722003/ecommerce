package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.CloudinaryService;
import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.*;
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

    @Autowired
    public CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Override
    public void getProductById(Long productId) {

        

    }

    @Override
    public List<ProductKeywordResponse> getProductsByKeyWord(String keyword) {
        List<Product> products = productRepo.getProductsByKeyword(keyword);
        List<ProductKeywordResponse> productKeywordResponses = new ArrayList<>();

        if (products.isEmpty()) throw new RuntimeException("Product does not found");

        for (Product product : products) {
            ProductKeywordResponse productKeywordResponse = mapper.map(product, ProductKeywordResponse.class);
            ImageResponse imageResponse = ImageResponse.builder()
                    .publicId(product.getThumbnailPublicId())
                    .avatarUrl(product.getThumbnailUrl())
                    .build();

            productKeywordResponse.setImageResponse(imageResponse);
            productKeywordResponses.add(productKeywordResponse);
        }

        return productKeywordResponses;
    }

    @Override
    @Transactional
    public ProductCreatingResponse createProduct(Long shopId, ProductCreatingDTO productCreatingDTO) {

        SubCategory category = categoryRepo.findById(productCreatingDTO.getSubcategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

        Shop shop = shopRepo.findById(shopId).orElseThrow(
                () ->  new RuntimeException("Shop does not found")
        );


        // create product response
        ProductCreatingResponse productCreatingResponse = new ProductCreatingResponse();
        productCreatingResponse.setName(productCreatingDTO.getName());
        productCreatingResponse.setDescription(productCreatingDTO.getDescription());
        productCreatingResponse.setSubcategoryId(productCreatingDTO.getSubcategoryId());
        productCreatingResponse.setSubcategoryName(category.getName());

        // save the avatar to the cloud
        Map<String, String> cloudAvatar;
        try {
             cloudAvatar = cloudinaryService.uploadImage(productCreatingDTO.getThumbnail());

             // add thumnail to response
             ImageResponse thumbnail = new ImageResponse();
            thumbnail.setAvatarUrl(cloudAvatar.get("imageUrl"));
            thumbnail.setPublicId(cloudAvatar.get("publicId"));
            productCreatingResponse.setThumbnail(thumbnail);

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

        productCreatingResponse.setProductId(savedProduct.getId());
        productCreatingResponse.setTotalSold(product.getTotalSold());

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

            ImageResponse productImageItem = new ImageResponse();
            productImageItem.setPublicId(cloudImage.get("publicId"));
            productImageItem.setAvatarUrl(cloudImage.get("imageUrl"));

            productCreatingResponse.addProductImage(productImageItem);

        }

        return productCreatingResponse;

    }

    @Override
    public QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId) {
        return null;
    }

    @Override
    public List<ProductRatingResponse> getProductsWithRatingOrder() {
        return List.of();
    }

}
