package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.ProductImageResponse;
import ecommerce.example.ecommerce.responses.ProductImageResponseInList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_image")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttributeValue productAttributeValue;


    public ProductImageResponse toProductImageResponse() {
        ProductImageResponse productImageResponse = ProductImageResponse.builder()
                .id(getId())
                .imageUrl(getUrl())
                .attributeId(getProductAttributeValue().getCategoryAttribute().getId())
                .attributeName(getProductAttributeValue().getCategoryAttribute().getAttribute().getName())
                .attributeValue(getProductAttributeValue().getValue())
                .attributeValueId(getProductAttributeValue().getId())
                .publicId(getPublicId())
                .productId(getProduct().getId())
                .build();
        return productImageResponse;
    }

    public ProductImageResponseInList toProductImageResponseInList() {
        ProductImageResponseInList productImageResponse = ProductImageResponseInList.builder()
                .id(getId())
                .imageUrl(getUrl())
                .attributeId(getProductAttributeValue().getCategoryAttribute().getId())
                .attributeName(getProductAttributeValue().getCategoryAttribute().getAttribute().getName())
                .attributeValue(getProductAttributeValue().getValue())
                .attributeValueId(getProductAttributeValue().getId())
                .publicId(getPublicId())
                .build();
        return productImageResponse;
    }

    @PrePersist
    public void prePersist() {

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
