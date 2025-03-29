package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductImageRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Autowired
    private ProductRepo productRepo;

//    @Autowired
//    private ProductAttributeValueRepo productAttributeValueRepo;

//    @Override
//    @Transactional
//    public ProductImageResponse addImageToProduct(MultipartFile file, Long productId, long productAttributeValueId) throws IOException {
//
//        Product product = productRepo.findById(productId).orElseThrow(
//                () -> new RuntimeException("Product does not found")
//        );
//
//        long productImageQuantities = productImageRepo.countImagesByProductId(productId);
//
//        ProductAttributeValue productAttributeValue = productAttributeValueRepo.findById(productAttributeValueId).orElseThrow(
//                () -> new RuntimeException("Product attribute value does not exist!!")
//        );
//
//        if (productImageQuantities > 10) {
//            throw new RuntimeException("Quantity of product image must be less or equal than 10");
//        }
//
//        try {
//            Map<String, String> cloudImage = cloudinaryService.uploadImage(file);
//
//            ProductImage productImage = ProductImage.builder()
////                    .productAttributeValue(productAttributeValue)
//                    .url(cloudImage.get("imageUrl"))
//                    .publicId(cloudImage.get("publicId"))
//                    .product(product)
//                    .build();
//
//            try {
//                productImageRepo.save(productImage);
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage());
//            }
//
//            ProductImageResponse productImageResponse = productImage.toProductImageResponse();
//
//            return productImageResponse;
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to save product image to cloud!!");
//        }
//
//
//
//
//    }
//
//    @Override
//    public List<ProductImageResponse> addMultipleProductImage(List<MultipartFile> files, Long productId) {
//        return List.of();
//    }
//
//    @Override
//    public ProductImageResponse findImageById(Long imageId) {
//        ProductImage productImage =  productImageRepo.findById(imageId).orElseThrow();
//        return productImage.toProductImageResponse();
//    }
//
//    @Override
//    public ProductImageListResponse findImageByProductId(Long productId) {
//        List<ProductImage> productImages = productImageRepo.findByProductId(productId);
//
//        List<ProductImageResponseInList> productImageResponse = productImages
//                .stream()
//                .map(productImage -> productImage.toProductImageResponseInList()
//        ).toList();
//
//        return ProductImageListResponse.builder()
//                .productId(productId)
//                .productImageResponseInLists(productImageResponse)
//                .build();
//    }
//
//    @Override
//    @Transactional
//    public void deleteImage(Long imageId, String publicId) {
//
//        ProductImage productImageExisting = productImageRepo.findById(imageId).orElseThrow(
//                () -> new RuntimeException("Image does not found")
//        );
//
//        productImageExisting.getProductAttributeValue().setProductImage(null);
//        productImageExisting.getProduct().deleteProductImages(imageId);
//
//
//        productImageRepo.delete(productImageExisting);
//
//        String response = cloudinaryService.deleteImage(publicId);
//        System.out.println(response);
//
//    }
}
