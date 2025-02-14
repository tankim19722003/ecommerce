package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "discount_amount")
    private int discountAmount;

    @Column(name = "shipping_cost")
    private int shippingCost;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "notes")
    private String notes;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "expected_receive_date")
    private LocalDate expectedReceiveDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_provider_id")
    private ShippingProvider shippingProvider;

}