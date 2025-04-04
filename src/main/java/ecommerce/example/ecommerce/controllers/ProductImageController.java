package ecommerce.example.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/image")
public class ProductImageController {

//    @Autowired
//    private ProductImageService productImageService;
//
//    @PostMapping(value = "/product_image/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("productId") Long productId,
//            @RequestParam("productAttributeValueId") Long productAttributeValueId
//    ) throws IOException {
//        try {
//            ProductImageResponse productImageResponse =  productImageService.addImageToProduct(file, productId, productAttributeValueId);
//            return ResponseEntity.ok(productImageResponse);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    EResponse.builder()
//                            .name("Error")
//                            .message(e.getMessage())
//                            .build()
//            );
//        }
//    }
//
//    @GetMapping("/{productImageId}")
//    public ResponseEntity<?> getProductImageResponseById(
//            @PathVariable("productImageId") Long productImageId
//    ) {
//
//        try {
//            ProductImageResponse productImageResponse =  productImageService
//                    .findImageById(productImageId);
//
//            return ResponseEntity.ok(productImageResponse);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    EResponse.builder()
//                            .name("ERROR")
//                            .message(e.getMessage())
//                            .build()
//            );
//        }
//    }
//
//    @DeleteMapping("")
//    public ResponseEntity<?> deleteImageById(
//            @RequestParam("imageId") Long imageId,
//            @RequestParam("publicId") String publicId
//    ) {
//
//        try {
//            productImageService.deleteImage(imageId, publicId);
//            return ResponseEntity.noContent().build();
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().body(
//                    EResponse.builder()
//                            .name("ERROR")
//                            .message(e.getMessage())
//                            .build()
//            );
//        }
//    }
//
//    @GetMapping("/get_all_product_images/{productId}")
//    public ProductImageListResponse getAllImagesByProductId(
//            @PathVariable("productId") Long productId
//    ) {
//
//        return productImageService.findImageByProductId(productId);
//    }



}
