package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "color_prices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"color", "product_size_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColorPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 30)
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_size_id", nullable = false)
    private ProductSize productSize;
}

