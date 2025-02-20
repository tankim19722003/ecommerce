package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.AddressResponse;
import ecommerce.example.ecommerce.responses.UserAddressResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_villages",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "village_id", "specific_address"}))
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private Village village;

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
                .build();
    }

    public AddressResponse toUserAddressResponse() {
        return AddressResponse.builder()
                .addressId(user.getId())
                .provinceId(village.getDistrict().getProvince().getId())
                .provinceName(village.getDistrict().getProvince().getName())
                .districtId(village.getDistrict().getId())
                .districtName(village.getName())
                .villageId(village.getId())
                .villageName(village.getName())
                .specificVillage(getSpecificVillage())
                .build();
    }
}
