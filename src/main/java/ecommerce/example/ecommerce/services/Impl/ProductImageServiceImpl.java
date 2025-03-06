package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductAttributeValueRepo;
import ecommerce.example.ecommerce.Repo.ProductImageRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductAttributeValue;
import ecommerce.example.ecommerce.models.ProductImage;
import ecommerce.example.ecommerce.responses.ProductImageResponse;
import ecommerce.example.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductAttributeValueRepo productAttributeValueRepo;

    @Override
    public ProductImageResponse addImageToProduct(MultipartFile file, Long productId, long productAttributeValueId) throws IOException {

        Product product = productRepo.findById(productId).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );

        long productImageQuantities = productImageRepo.countImagesByProductId(productId);

        ProductAttributeValue productAttributeValue = productAttributeValueRepo.findById(productAttributeValueId).orElseThrow(
                () -> new RuntimeException("Product attribute value does not exist!!")
        );

        if (productImageQuantities > 10) {
            throw new RuntimeException("Quantity of product image must be less or equal than 10");
        }

        try {
            Map<String, String> cloudImage = cloudinaryService.uploadImage(file);

            ProductImage productImage = ProductImage.builder()
                    .productAttributeValue(productAttributeValue)
                    .url(cloudImage.get("imageUrl"))
                    .publicId(cloudImage.get("publicId"))
                    .product(product)
                    .build();

            productImageRepo.save(productImage);

            ProductImageResponse productImageResponse = ProductImageResponse.builder()
                    .id(productImage.getId())
                    .imageUrl(productImage.getUrl())
                    .attributeId(productAttributeValue.getCategoryAttribute().getId())
                    .attributeName(productAttributeValue.getCategoryAttribute().getAttribute().getName())
                    .attributeValue(productAttributeValue.getValue())
                    .publicId(productImage.getPublicId())
                    .build();

            return productImageResponse;

        } catch (Exception e) {
            throw new RuntimeException("Failed to save product image to cloud!!");
        }




    }

    @Override
    public List<ProductImageResponse> addMultipleProductImage(List<MultipartFile> files, Long productId) {
        return List.of();
    }

    @Override
    public ProductImageResponse findImageById(Long imageId) {
        return null;
    }

    @Override
    public List<ProductImageResponse> findImageByProductId(Long ProductId) {
        return List.of();
    }

    @Override
    public void deleteImage(Long imageId) {

    }
}
