//package ecommerce.example.ecommerce.services.Impl;
//
//import ecommerce.example.ecommerce.Repo.ProductAttributeValueRepo;
//import ecommerce.example.ecommerce.responses.AttributeValueResponse;
//import ecommerce.example.ecommerce.services.ProductAttributeValueService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {
//
//    @Autowired
//    private ProductAttributeValueRepo productAttributeValueRepo;
//
//    @Override
//    public List<AttributeValueResponse> getAllProductAttributeValuesByProductId(Long productId) {
//
//        List<ProductAttributeValue> productAttributeValues = productAttributeValueRepo
//                .findAllByProductId(productId);
//
//        List<AttributeValueResponse> productAttributeValuesResponses = productAttributeValues
//                .stream()
//                .map( productAttributeValue -> {
//                   return AttributeValueResponse.builder()
//                           .productAttributeValueId(productAttributeValue.getId())
//                           .attributeId(productAttributeValue.getCategoryAttribute().getId())
//                           .attributeName(productAttributeValue.getCategoryAttribute().getAttribute().getName())
//                           .value(productAttributeValue.getValue())
//                           .build();
//                }).toList();
//
//        return productAttributeValuesResponses;
//    }
//}
