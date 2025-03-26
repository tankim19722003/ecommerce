package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.dtos.ShopDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "village_id")
    private Village village;

    @Column(name = "specific_address")
    private String specificAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "front_cmnd_url")
    private String frontCmndUrl;

    @Column(name = "front_cmnd_public_id")
    private String frontCmndPublicId;

    @Column(name = "behind_cmnd_url")
    private String behindCmndUrl;

    @Column(name = "behind_cmnd_public_id")
    private String behindCmndPublicId;

//    @Pattern(regexp = "PENDING|REJECT|COMPLETION", message = "Invalid status value")
//    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @JsonProperty("email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonIgnore
    private List<Product> products;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonIgnore
    private ShopBanned shopBanned;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonIgnore
    private ShopRejection shopRejection;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    @JsonIgnore
    private List<Coupon>coupons;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShopAddress> shopAddress;

    @PrePersist
    public void setDefaultStatus() {
        if (this.logo == null) {
            this.logo = "shop.png";
        }

        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void setUpdate() {
        this.updatedAt = LocalDate.now();
    }


    public Shop fromShopDTO(ShopDTO shopDTO, Village village) {
        return Shop.builder()
                .shopName(shopDTO.getShopName())
                .description(shopDTO.getDescription())
                .specificAddress(shopDTO.getSpecificAddress())
                .village(village)
                .phoneNumber(shopDTO.getPhoneNumber())
                .email(shopDTO.getEmail())
                .build();
    }

}