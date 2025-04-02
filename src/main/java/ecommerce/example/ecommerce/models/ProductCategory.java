package ecommerce.example.ecommerce.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_categories",
        uniqueConstraints = @UniqueConstraint(columnNames = {"first_category", "second_category"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_category", nullable = false)
    private String firstCategory;

    @Column(name = "second_category", nullable = false)
    private String secondCategory;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "publicId")
    private String publicId;

    @Column(name = "image_url")
    private String imageUrl;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_category_group_id")
    private ProductCategoryGroup productCategoryGroup;
}
