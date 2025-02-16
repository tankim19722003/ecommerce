package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "shop_rejections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopRejection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rejected_reason")
    private String rejectedReason;

    @Column(name = "rejected_date")
    private LocalDateTime rejectedDate;

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}