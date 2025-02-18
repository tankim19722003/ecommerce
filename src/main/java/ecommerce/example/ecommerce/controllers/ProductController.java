//package ecommerce.example.ecommerce.controllers;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("${api.prefix}product")
//public class ProductController {
//
//    @GetMapping("")
//    public ResponseEntity<ProductListResponse> getProducts(
//            @RequestParam(defaultValue = "") String keyword,
//            @RequestParam(defaultValue = "0", name = "category_id" ) Long categoryId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int limit
//    )    {
//        PageRequest pageRequest = PageRequest.of(
//                page,limit,
//                Sort.by("rating").ascending()
//        );
//
//        logger.info(String.format("keyword = %s, category_id = %d, page %d, limit = %d",
//                keyword, categoryId, page, limit));
//        Page<ProductResponse> productPage = productService.getAllProducts(keyword,categoryId,pageRequest);
//        int totalPages = productPage.getTotalPages();
//        List <ProductResponse> products = productPage.getContent();
//
//        return ResponseEntity.ok(
//                ProductListResponse.builder()
//                        .products(products)
//                        .totalPage(totalPages)
//                        .build()
//        );
//    }
//
//}
