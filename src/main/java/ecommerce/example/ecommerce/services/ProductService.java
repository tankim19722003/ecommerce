package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;

public interface ProductService {

    ProductCreatingResponse createProduct(ProductCreatingDTO productCreatingDTO);
}
