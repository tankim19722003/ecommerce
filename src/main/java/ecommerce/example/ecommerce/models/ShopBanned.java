package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "shop_banned")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopBanned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String banReason;
    private LocalDateTime banStartDate;
    private LocalDateTime banEndDate;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}

