package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product_shipping_types",
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id", nullable = false)
    private ShippingType shippingType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productShippingType")
    private List<Order> orders;
}
