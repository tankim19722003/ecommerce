package ecommerce.example.ecommerce.models;

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
}
