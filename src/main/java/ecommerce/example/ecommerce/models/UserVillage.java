package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.AddressResponse;
import ecommerce.example.ecommerce.responses.UserAddressResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_villages",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "village_id", "specific_village"}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVillage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "specific_village")
    private String specificVillage;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private Village village;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public UserAddressResponse toUserVillageResponse() {
        return UserAddressResponse.builder()
                .addressId(user.getId())
                .userId(user.getId())
                .provinceId(village.getDistrict().getProvince().getId())
                .provinceName(village.getDistrict().getProvince().getName())
                .districtId(village.getDistrict().getId())
                .districtName(village.getName())
                .villageId(village.getId())
                .villageName(village.getName())
                .specificVillage(getSpecificVillage())
                .phoneNumber(getPhoneNumber())
                .receiverName(getReceiverName())
                .createdAt(createdAt)
                .updatedAt(updateAt)
                .build();
    }

    public AddressResponse toUserAddressResponse() {
        return AddressResponse.builder()
                .addressId(getId())
                .provinceId(village.getDistrict().getProvince().getId())
                .provinceName(village.getDistrict().getProvince().getName())
                .districtId(village.getDistrict().getId())
                .districtName(village.getName())
                .villageId(village.getId())
                .villageName(village.getName())
                .specificVillage(getSpecificVillage())
                .phoneNumber(getPhoneNumber())
                .receiverName(getReceiverName())
                .createdAt(createdAt)
                .updatedAt(updateAt)
                .build();
    }
}
