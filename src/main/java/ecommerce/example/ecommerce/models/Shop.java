package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_name", unique = true)
    private String shopName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;

    @Column(name = "specific_address")
    private String specificAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Pattern(regexp = "PENDING|REJECT|COMPLETION", message = "Invalid status value")
    private String status;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<Product> products;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private ShopBanned shopBanned;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shop")
    private ShopRejection shopRejection;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<Coupon>coupons;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopAddress> shopAddress;

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = "PENDING";
        }

        if (this.logo == null) {
            this.logo = "shop.png";
        }
    }




}