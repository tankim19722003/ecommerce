package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.MultipleProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryGroupDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.MultipleProductCategoryResponse;
import ecommerce.example.ecommerce.responses.ProductCategoryOneLevelResponse;
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
            List<ProductCategoryOneLevelResponse> productCategoryResponses= productCategoryService
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

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductCategoryByProductId(
        @PathVariable("productId") Long productId
    ) {

        try {
            ProductCategoryResponse productCategoryResponse = productCategoryService.getProductCategories(productId);
            return ResponseEntity.ok(productCategoryResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }
}
