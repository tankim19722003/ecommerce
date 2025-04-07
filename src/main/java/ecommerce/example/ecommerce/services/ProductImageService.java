package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.ImageResponse;

import java.util.List;

public interface ProductImageService {

    List<ImageResponse> getProductImages(Long productId);

}
