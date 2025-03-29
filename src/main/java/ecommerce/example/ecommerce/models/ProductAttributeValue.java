//package ecommerce.example.ecommerce.models;
//
//import ecommerce.example.ecommerce.responses.AttributeValueImageResponse;
//import ecommerce.example.ecommerce.responses.AttributeValueResponse;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "product_attribute_value")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//public class ProductAttributeValue {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "value")
//    private String value;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "sub_category_attribute_id")
//    private SubCategoryAttribute categoryAttribute;
//
//
//    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "productAttributeValue")
//    private ProductImage productImage;
//
//    public AttributeValueResponse toProductAttributeResponse() {
//
//        if (productImage != null) {
//            AttributeValueImageResponse attributeValueResponse = new AttributeValueImageResponse();
//            attributeValueResponse.setImageId(productImage.getId());
//            attributeValueResponse.setImageUrl(productImage.getUrl());
//            attributeValueResponse.setPublicId(productImage.getPublicId());
//            attributeValueResponse.setAttributeId(categoryAttribute.getAttribute().getId());
//            attributeValueResponse.setAttributeName(categoryAttribute.getAttribute().getName());
//            attributeValueResponse.setValue(value);
//            attributeValueResponse.setProductAttributeValueId(id);
//            return attributeValueResponse;
//        }
//        AttributeValueResponse attributeValueResponse = AttributeValueResponse.builder()
//                .attributeId(categoryAttribute.getAttribute().getId())
//                .attributeName(categoryAttribute.getAttribute().getName())
//                .value(value)
//                .productAttributeValueId(id)
//                .build();
//
//
//
//        return attributeValueResponse;
//    }
//
//}
