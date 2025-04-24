package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ecommerce.example.ecommerce.responses.AddressResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "villages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "village")
    private List<UserVillage> userVillages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "village")
    private List<ShopAddress> shopAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "village")
    private List<Shop> shops;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "village")
    private List<SupplierAddress> supplierAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "village")
    private List<UserVillageOrder> userVillageOrders;

    public AddressResponse toAddressResponse() {
        AddressResponse addressResponse = new AddressResponse();

        addressResponse.setVillageId(id);
        addressResponse.setVillageName(name);
        addressResponse.setDistrictId(district.getId());
        addressResponse.setDistrictName(district.getName());
        addressResponse.setProvinceId(district.getProvince().getId());
        addressResponse.setProvinceName(district.getProvince().getName());

        return addressResponse;
    }
}
