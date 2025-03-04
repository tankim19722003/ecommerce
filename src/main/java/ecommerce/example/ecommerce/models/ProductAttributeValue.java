package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_attribute_id")
    private CategoryAttribute categoryAttribute;

//    @OneToMany(mappedBy = "firstProductAttributeValue")
//    private List<Quantity> firstQuantity;
//
//    @OneToMany(mappedBy = "secondProductAttributeValue")
//    private List<Quantity> secondQuantity;
}
