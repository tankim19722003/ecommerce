//package ecommerce.example.ecommerce.controllers;
//
//import ecommerce.example.ecommerce.responses.AttributeValueResponse;
////import ecommerce.example.ecommerce.services.ProductAttributeValueService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("${api.prefix}/product_attribute_value")
//public class ProductAttributeValueController {
//
//    @Autowired
////    private ProductAttributeValueService productAttributeValueService;
//
//    @GetMapping("/{productId}")
//    public List<AttributeValueResponse> getAllProductAttributeValues(
//            @PathVariable("productId") long productId
//    ) {
//        return productAttributeValueService
//                .getAllProductAttributeValuesByProductId(productId);
//    }
//}
