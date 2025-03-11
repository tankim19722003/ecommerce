package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.ProductImageListResponse;
import ecommerce.example.ecommerce.responses.ProductImageResponse;
import ecommerce.example.ecommerce.responses.ProductImageResponseInList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {

    ProductImageResponse addImageToProduct(MultipartFile file, Long productId, long productAttributeValueId) throws IOException;

    ProductImageResponse findImageById(Long imageId);


    List<ProductImageResponse> addMultipleProductImage(List<MultipartFile> files, Long productId);

    ProductImageListResponse findImageByProductId(Long productId);

    void deleteImage(Long imageId, String publicId);
}
