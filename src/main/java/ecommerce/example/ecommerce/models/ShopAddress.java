package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shop_addresses",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"shop_id", "specific_address", "village_id"}
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private Village village;

    @Column(name = "specific_address")
    private String specificAddress;

    @Column(name = "shop_owner_name")
    private String shopOwnerName;

    @Column(name = "phone_number")
    private String phoneNumber;
}
