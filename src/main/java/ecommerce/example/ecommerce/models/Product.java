package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(columnNames = {"shop_id", "name", "category_id"})
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

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @Column(name = "total_sold")
    private int totalSold;

    private String thumbnail;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    private List<CartItem> cartItems;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Feedback> feedbacks;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ProductRejection productRejection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductDiscount> productDiscounts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductAttributeValue> ProductAttributeValues;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Quantity> quantities;

    public void addProductAttribute(ProductAttributeValue productAttributeValue) {
        if (ProductAttributeValues == null) {
            ProductAttributeValues = new ArrayList<>();
        }
        ProductAttributeValues.add(productAttributeValue);
    }

    @PrePersist
    public void prePersist() {
        if (this.totalSold == 0) {
            this.totalSold = 0;
        }

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
