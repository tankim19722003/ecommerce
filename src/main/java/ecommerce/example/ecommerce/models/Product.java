package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(columnNames = {"shop_id", "name", "sub_category_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name ="thumbnail_public_id")
    private String thumbnailPublicId;

    // shipping info
    @Column(name = "height")
    private Integer height;

    @Column(name = "high")
    private Integer high;

    @Column(name = "width")
    private Integer width;

    @Column(name = "weight")
    private Float weight;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    private List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ProductRejection productRejection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> images;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductDiscount> productDiscounts;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryGroup> productCategoryGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductShippingType> productShippingTypes;

    @PrePersist
    public void prePersist() {

        this.totalSold = 0;
        this.rating = 0d;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void deleteProductImages(Long id) {
        for (ProductImage productImage : images) {
            if (productImage.getId() == id) {
                images.remove(productImage);
                break;  
            }
        }
    }

}
