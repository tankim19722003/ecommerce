package ecommerce.example.ecommerce.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shipping_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShippingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "estimated_time")
    private int estimatedTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shippingType")
    private List<ShippingTypeProvider> shippingTypeProviders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shippingType")
    private List<ProductShippingType> productShippingTypes;

}
