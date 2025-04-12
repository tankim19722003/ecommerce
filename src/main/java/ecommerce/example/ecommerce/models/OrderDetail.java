package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.OrderDetailResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "discount_percent")
    private int discountPercent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderDetail")
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "sub_product_category_id")
    private SubProductCategory subProductCategory;

    public OrderDetailResponse toOrderDetailResponse() {
        return OrderDetailResponse.builder()
                .id(id)
                .discountPercent(discountPercent)
                .productName(product.getName())
                .price(price)
                .quantity(quantity)
                .build();
    }

}
