package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.UserVillageOrderResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_village_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVillageOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "village_id")
    private Village village;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "specific_address")
    private String specificAddress;

    public UserVillageOrderResponse toUserVillageOrderResponse() {
        UserVillageOrderResponse userVillageOrderResponse = new UserVillageOrderResponse();

        userVillageOrderResponse.setPhoneNumber(order.getPhoneNumber());
        userVillageOrderResponse.setReceiverName(order.getReceiverName());
        userVillageOrderResponse.setSpecificAddress(specificAddress);
        userVillageOrderResponse.setVillageId(village.getId());
        userVillageOrderResponse.setVillageName(village.getName());
        userVillageOrderResponse.setDistrictId(village.getDistrict().getId());
        userVillageOrderResponse.setDistrictName(village.getDistrict().getName());
        userVillageOrderResponse.setProvinceId(village.getDistrict().getProvince().getId());
        userVillageOrderResponse.setProvinceName(village.getDistrict().getProvince().getName());

        return userVillageOrderResponse;

    }
}
