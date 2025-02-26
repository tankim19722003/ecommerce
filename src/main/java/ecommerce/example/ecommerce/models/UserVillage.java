package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.UserVillageResponse;
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

        UserAddressResponse userAddressResponse = new UserAddressResponse();
        userAddressResponse.setAddressId(user.getId());
        userAddressResponse.setUserId(user.getId());
        userAddressResponse.setProvinceId(village.getDistrict().getProvince().getId());
        userAddressResponse.setProvinceName(village.getDistrict().getProvince().getName());
        userAddressResponse.setDistrictId(village.getDistrict().getId());
        userAddressResponse.setDistrictName(village.getDistrict().getName());
        userAddressResponse.setVillageId(village.getId());
        userAddressResponse.setVillageName(village.getName());
        userAddressResponse.setSpecificVillage(getSpecificVillage());
        userAddressResponse.setPhoneNumber(getPhoneNumber());
        userAddressResponse.setReceiverName(getReceiverName());
        userAddressResponse.setCreatedAt(createdAt);
        userAddressResponse.setUpdatedAt(updateAt);


        return userAddressResponse;
    }

    public UserVillageResponse toUserAddressResponse() {
        UserVillageResponse userVillageResponse = new UserVillageResponse();

        userVillageResponse.setAddressId(id);
        userVillageResponse.setProvinceId(village.getDistrict().getProvince().getId());
        userVillageResponse.setProvinceName(village.getDistrict().getProvince().getName());
        userVillageResponse.setDistrictId(village.getDistrict().getId());
        userVillageResponse.setDistrictName(village.getName());
        userVillageResponse.setVillageId(village.getId());
        userVillageResponse.setVillageName(village.getName());
        userVillageResponse.setSpecificAddress(getSpecificVillage());
        userVillageResponse.setPhoneNumber(getPhoneNumber());
        userVillageResponse.setReceiverName(getReceiverName());
        userVillageResponse.setCreatedAt(createdAt);
        userVillageResponse.setUpdatedAt(updateAt);

        return userVillageResponse;
    }
}
