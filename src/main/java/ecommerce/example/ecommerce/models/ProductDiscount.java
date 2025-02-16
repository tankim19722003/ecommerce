package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_discounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDiscount {
    @Id
    @Column(name = "id")
    private Long id;


    @Column(name = "discount_percent", nullable = false)
    private Float discountPercent;

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
