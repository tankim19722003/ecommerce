package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.MultipleProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryGroupDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.MultipleProductCategoryResponse;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/add_multiple/one_level", consumes = "multipart/form-data")
    public ResponseEntity<?> addMultipleCategoryOneLevel(
        @RequestParam("productId") Long productId,
        @RequestParam("shopId") Long shopId,
        @RequestPart("productCategoryRequest") ProductCategoryGroupDTO productCategoryGroupDTO,
        @RequestPart("files") List<MultipartFile> files
    ) {

        try {
            ownerService.checkValidShop(shopId);
            List<ProductCategoryResponse> productCategoryResponses= productCategoryService
                    .addMultipleProductCategoryOneLevel(productId, productCategoryGroupDTO, files);

            return ResponseEntity.ok(productCategoryResponses);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping(value = "/add_multiple/two_level", consumes = "multipart/form-data")
    public ResponseEntity<?> addMultipleCategoryTwoLevel(
            @RequestParam("productId") Long productId,
            @RequestParam("shopId") Long shopId,
            @RequestPart("multipleProductCategoryDTO") MultipleProductCategoryDTO multipleProductCategoryDTO,
            @RequestPart("files") List<MultipartFile> files
    ) {

        try {
            ownerService.checkValidShop(shopId);
            MultipleProductCategoryResponse productCategoryResponses= productCategoryService
                    .addMultipleProductCategoryTwoLevel(productId,shopId, multipleProductCategoryDTO, files);

            return ResponseEntity.ok(productCategoryResponses);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

//    @PostMapping("/add_one")
//    public ResponseEntity<?> addProductCategory(
//            @RequestParam("productId") Long productId,
//            @RequestParam("shopId") long shopId,
//            @ModelAttribute ProductCategoryImageDTO productCategoryImageDTO
//    ) {
//
//        ownerService.checkValidShop(shopId);
//        try {
//            ProductCategoryResponse productCategoryResponse = productCategoryService
//                    .addProductCategory(productId, productCategoryImageDTO);
//
//            return ResponseEntity.ok(productCategoryResponse);
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
//    @GetMapping("/{productId}")
//    public List<ProductCategoryResponse> getAllProductCategory(
//        @PathVariable("productId") long productId
//    ) {
//        return productCategoryService.getAllProductCategoriesByProductId(productId);
//    }
//
//    @PutMapping("")
//    public ResponseEntity<?> updateProductCategory(
//            @RequestParam("productCategoryId") Long productCategoryId,
//            @RequestParam("shopId") long shopId,
//            @RequestBody ProductCategoryDTO productCategoryDTO
//    ) {
//
//        ownerService.checkValidShop(shopId);
//
//        try {
//            ProductCategoryResponse productCategoryResponse =  productCategoryService
//                    .updateProductCategory(productCategoryId, productCategoryDTO);
//
//            return ResponseEntity.ok(productCategoryResponse);
//        } catch(Exception e) {
//            return ResponseEntity.badRequest()
//                    .body(EResponse.builder()
//                            .name("ERROR")
//                            .message(e.getMessage())
//                            .build());
//        }
//    }
//
//    @DeleteMapping("")
//    public ResponseEntity<?> deleteProductCategory(
//            @RequestParam("shopId") long shopId,
//            @RequestParam("productCategoryId") Long productCategoryId
//    ) {
//        ownerService.checkValidShop(shopId);
//        try {
//            productCategoryService.deleteProductCategoryById(productCategoryId);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    EResponse.builder()
//                            .name("ERROR")
//                            .message(e.getMessage())
//                            .build()
//            );
//        }
//
//    }
}
