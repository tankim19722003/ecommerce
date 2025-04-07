package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductImageRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.dtos.CloudinaryService;
import ecommerce.example.ecommerce.models.ProductImage;
import ecommerce.example.ecommerce.responses.ImageResponse;
import ecommerce.example.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Autowired
    private ProductRepo productRepo;


    @Override
    public List<ImageResponse> getProductImages(Long productId) {

        // check is product existing
        Boolean isProductExisting = productRepo.existsById(productId);
        if (!isProductExisting)
            throw new RuntimeException("Product does not found");

        List<ProductImage> productImages = productImageRepo.findByProductId(productId);

        if (productImages != null) {
            return productImages
                    .stream()
                    .map( image ->
                        ImageResponse
                                .builder()
                                .avatarUrl(image.getUrl())
                                .publicId(image.getPublicId())
                                .build()
                    ).toList();
        }

        return null;
    }
}
