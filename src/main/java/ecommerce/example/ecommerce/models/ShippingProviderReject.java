package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipping_provider_rejects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingProviderReject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rejected_reason", nullable = false)
    private String rejectedReason;

    @Column(name = "rejected_date")
    private LocalDateTime rejectedDate;

    @ManyToOne
    @JoinColumn(name = "shipping_provider_id", nullable = false)
    private ShippingProvider shippingProvider;
}