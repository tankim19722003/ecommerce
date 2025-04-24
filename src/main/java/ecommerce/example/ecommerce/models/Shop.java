package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.dtos.ShopDTO;
import ecommerce.example.ecommerce.responses.AddressResponse;
import ecommerce.example.ecommerce.responses.ShopBasicInfoResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;

    @Column(name = "total_money")
    private Integer totalMoney;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, mappedBy = "shop")
    @JsonIgnore
    private List<SupplierAddress> supplierAddresses;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @JsonProperty("email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    @JsonManagedReference
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop")
    @JsonManagedReference
    private ShopBanned shopBanned;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop")
    @JsonManagedReference
    private ShopRejection shopRejection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    @JsonManagedReference
    private List<Voucher> vouchers;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ShopAddress> shopAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    @JsonManagedReference
    private List<Order> orders;

    @PrePersist
    public void setDefaultStatus() {
        if (this.logo == null) {
            this.logo = "shop.png";
        }

        this.totalMoney = 0;
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

    public ShopBasicInfoResponse toShopBasicInfo() {

        AddressResponse addressResponse = village.toAddressResponse();
        ShopBasicInfoResponse shopBasicInfoResponse =  ShopBasicInfoResponse.builder()
                .id(id)
                .shopName(shopName)
                .specificAddress(specificAddress)
                .phoneNumber(phoneNumber)
                .logo(logo)
                .description(description)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .addressResponse(addressResponse)
                .build();

        return shopBasicInfoResponse;
    }

}