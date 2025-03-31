package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping_type_providers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShippingTypeProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "shipping_provider", nullable = false)
    private ShippingProvider shippingProvider;

    @ManyToOne
    @JoinColumn(name = "shipping_type", nullable = true)
    private ShippingType shippingType;
}
