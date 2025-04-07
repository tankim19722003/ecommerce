package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import ecommerce.example.ecommerce.responses.ProductKeywordResponse;
import ecommerce.example.ecommerce.responses.ProductRatingOrderResponse;
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

    @GetMapping("/get_by_key_word/{keyword}")
    public ResponseEntity<?> getProductsByKeyword(
            @PathVariable("keyword") String keyword
    ) {
        try {
            List<ProductKeywordResponse> productKeywordResponses =  productService.getProductsByKeyWord(keyword);
            return ResponseEntity.ok(productKeywordResponses);
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {

        PageRequest pageRequest = PageRequest.of(
                page,limit,
                Sort.by("rating").ascending()
        );

        try {
            List<ProductRatingOrderResponse> productKeywordResponses =  productService
                    .getProductsWithRatingOrder(pageRequest);
            return ResponseEntity.ok(productKeywordResponses);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

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
            List<ProductRatingOrderResponse> productRatingOrderResponses = productService
                    .getProductsWithRatingOrder(pageRequest);

            return ResponseEntity.ok(productRatingOrderResponses);
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
