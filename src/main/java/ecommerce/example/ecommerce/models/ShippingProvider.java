package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "shipping_speed")
    private String shippingSpeed; // express

    @Column(name = "shipping_cost")
    private int shippingCost;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY, mappedBy = "shippingProvider")
    private List<Order> orders;
}