package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantity",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"first_product_attribute_id", "second_product_attribute_id", "product_id"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "first_product_attribute_id")
    private ProductAttributeValue firstProductAttributeValue;

    @ManyToOne
    @JoinColumn(name = "second_product_attribute_id")
    private ProductAttributeValue secondProductAttributeValue;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
