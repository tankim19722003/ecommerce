package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {

    ProductImageResponse addImageToProduct(MultipartFile file, Long productId, long productAttributeValueId) throws IOException;

    List<ProductImageResponse> addMultipleProductImage(List<MultipartFile> files, Long productId);

    ProductImageResponse findImageById(Long imageId);

    List<ProductImageResponse> findImageByProductId(Long ProductId);

    void deleteImage(Long imageId);
}
