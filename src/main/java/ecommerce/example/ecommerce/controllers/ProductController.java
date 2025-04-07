package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/create_product/{shopId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
        @PathVariable("shopId") Long shopId,
        @ModelAttribute ProductCreatingDTO productCreatingDTO
    ) {

        try {
            ownerService.checkValidShop(shopId);
            ProductCreatingResponse productCreatingResponse =  productService.createProduct(shopId, productCreatingDTO);
            return ResponseEntity.ok().body(productCreatingResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @GetMapping("/get_all_product_by_key_word")
    public ResponseEntity<?> getProductsByKeyword(
            @RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(
                page,limit,
                Sort.by(
                        Sort.Order.desc("totalSold"),
                        Sort.Order.desc("rating")
                )
        );
        try {
            ProductKeywordPageResponse productKeywordPageResponse =  productService.getProductsByKeyWord(pageRequest, keyword);
            return ResponseEntity.ok(productKeywordPageResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

//    @GetMapping("/get_all_product_by_key_word")
//    public ResponseEntity<?> getProductsByKeyword(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int limit
//    ) {
//
//        PageRequest pageRequest = PageRequest.of(
//                page,limit,
//                Sort.by(
//                        Sort.Order.desc("totalSold"),
//                        Sort.Order.desc("rating")
//                )
//        );
//
//        try {
//            List<ProductRatingOrderResponse> productKeywordResponses =  productService
//                    .getProductsWithRatingOrder(pageRequest);
//            return ResponseEntity.ok(productKeywordResponses);
//        } catch(Exception e) {
//            return ResponseEntity.badRequest().body(
//                    EResponse.builder()
//                            .name("ERROR")
//                            .message(e.getMessage())
//                            .build()
//            );
//        }
//    }

    @GetMapping("/get_all_with_rating_order")
    public ResponseEntity<?> getProductWithRatingOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(
                page,limit,
                Sort.by(
                        Sort.Order.desc("totalSold"),
                        Sort.Order.desc("rating")
                )
        );
        try {
            ProductRatingOrderPageResponse productRatingOrderPageResponse = productService
                    .getProductsWithRatingOrder(pageRequest);

            return ResponseEntity.ok(productRatingOrderPageResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/get_product_detail/{productId}")
    public ResponseEntity<?> getAllProductDetails(
            @PathVariable("productId") Long productId
    ) {

        try {
            ProductDetailResponse productDetailResponse = productService.getProductDetails(productId);

            return ResponseEntity.ok(productDetailResponse);
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
