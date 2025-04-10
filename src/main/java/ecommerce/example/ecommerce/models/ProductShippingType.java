package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_shipping_type",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id", "shipping_type_id"})
        })
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductShippingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id", nullable = false)
    private ShippingType shippingType;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "high")
    private int high;

    @Column(name = "weight")
    private float weight;
}
