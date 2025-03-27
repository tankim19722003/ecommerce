package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.AttributeSubIdResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sub_category_attributes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"attribute_id", "sub_category_id"})
        })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubCategoryAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;


    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryAttribute")
    private List<ProductAttributeValue> productAttributeValues;

    public SubcategoryAttributeResponse toSubcategoryAttributeResponse() {
        SubcategoryAttributeResponse response = new SubcategoryAttributeResponse();

        response.setAttributeId(attribute.getId());
        response.setAttributeValue(attribute.getName());
        response.setSubcategoryId(subCategory.getId());
        response.setSubcategoryValue(subCategory.getName());
        response.setSubcategoryAttributeId(id);

        return response;
    }

    public AttributeSubIdResponse toAttributeSubIdResponse() {
        return AttributeSubIdResponse.builder()
                .attributeId(attribute.getId())
                .attributeValue(attribute.getName())
                .subcategoryAttributeId(id)
                .build();
    }
}
