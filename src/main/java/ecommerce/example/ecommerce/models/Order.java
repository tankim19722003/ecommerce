package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public static final String PENDING = "PENDING";
    public static final String PACKING = "PACKING";
    public static final String SHIPPING = "SHIPPING";
    public static final String COMPLETED = "COMPLETED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "payment_method")
    private String paymentMethod;

    @JsonProperty("status")
    private String status;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "notes")
    private String notes;

    @Column(name = "expected_receive_date")
    private LocalDateTime expectedReceiveDate;

    @JsonProperty("receiver_name")
    private String receiverName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "shipping_provider_id")
    private ShippingProvider shippingProvider;

//    @ManyToOne
//    @JoinColumn(name = "shipping_type_id")
//    private ShippingType shippingType;

    @ManyToOne
    @JoinColumn(name = "product_shipping_type_id", nullable = false)
    private ProductShippingType productShippingType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_address_id")
    private UserVillage userVillage;

    @OneToOne
    @JoinColumn(name = "user_village_order_id")
    private UserVillageOrder userVillageOrder;

}