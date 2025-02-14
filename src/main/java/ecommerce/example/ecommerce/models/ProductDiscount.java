package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_discounts")
public class ProductDiscount {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_discounts_products"))
    private Product product;

    @Column(name = "discount_percent", nullable = false)
    private Float discountPercent;

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;
}
