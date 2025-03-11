package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute_value")
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
    private SubCategoryAttribute categoryAttribute;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productAttributeValue")
    private ProductImage productImage;
}
