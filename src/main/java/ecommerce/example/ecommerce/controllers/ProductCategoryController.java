package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCategoryDTO;
import ecommerce.example.ecommerce.dtos.ProductCategoryImageDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductCategoryResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product_category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/add_multiple/{productId}")
    public ResponseEntity<?> addMultipleCategory(
        @RequestParam("productId") Long productId,
        @RequestParam("shopId") long shopId,
        @ModelAttribute List<ProductCategoryImageDTO> productCategoryImageDTO
    ) {
        try {
            ownerService.checkValidShop(shopId);
            List<ProductCategoryResponse> productCategoryResponses = productCategoryService
                    .addMultipleProductCategory(productId, productCategoryImageDTO);

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

    @PostMapping("/add_one")
    public ResponseEntity<?> addProductCategory(
            @RequestParam("productId") Long productId,
            @RequestParam("shopId") long shopId,
            @ModelAttribute ProductCategoryImageDTO productCategoryImageDTO
    ) {

        ownerService.checkValidShop(shopId);
        try {
            ProductCategoryResponse productCategoryResponse = productCategoryService
                    .addProductCategory(productId, productCategoryImageDTO);

            return ResponseEntity.ok(productCategoryResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/{productId}")
    public List<ProductCategoryResponse> getAllProductCategory(
        @PathVariable("productId") long productId
    ) {
        return productCategoryService.getAllProductCategoriesByProductId(productId);
    }

    @PutMapping("")
    public ResponseEntity<?> updateProductCategory(
            @RequestParam("productCategoryId") Long productCategoryId,
            @RequestParam("shopId") long shopId,
            @RequestBody ProductCategoryDTO productCategoryDTO
    ) {

        ownerService.checkValidShop(shopId);

        try {
            ProductCategoryResponse productCategoryResponse =  productCategoryService
                    .updateProductCategory(productCategoryId, productCategoryDTO);

            return ResponseEntity.ok(productCategoryResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteProductCategory(
            @RequestParam("shopId") long shopId,
            @RequestParam("productCategoryId") Long productCategoryId
    ) {
        ownerService.checkValidShop(shopId);
        try {
            productCategoryService.deleteProductCategoryById(productCategoryId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }
}
