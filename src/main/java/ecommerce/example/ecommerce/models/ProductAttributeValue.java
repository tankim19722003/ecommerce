package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.ProductAttributeValueResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute_value",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "sub_category_attribute_id"})
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sub_category_attribute_id")
    private SubCategoryAttribute subcategoryAttribute;


    public ProductAttributeValueResponse toProductAttributeValueResponse() {
        return ProductAttributeValueResponse.builder()
                .productAttributeValueId(id)
                .value(value)
                .attributeName(subcategoryAttribute.getAttribute().getName())
                .build();
    }

}
