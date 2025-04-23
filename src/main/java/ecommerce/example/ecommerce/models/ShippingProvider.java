package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.ShippingProviderResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shipping_providers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY, mappedBy = "shippingProvider")
    private List<Order> orders;

//    @Column(name = "shipping_provider_money")
//    private int shippingProviderMoney;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ShippingProviderResponse toShippingProviderResponse() {
        return ShippingProviderResponse
                .builder()
                .id(id)
                .phone(user.getPhoneNumber())
                .email(user.getEmail())
                .account(user.getAccount())
                .createdAt(user.getCreatedAt())
                .status(status)
                .name(name)
                .build();
    }

//    @PrePersist
//    public void prePersist() {
//        this.shippingProviderMoney = 0;
//    }
}