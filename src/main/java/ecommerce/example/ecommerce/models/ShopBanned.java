package ecommerce.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "ban_reason")
    private String banReason;

    @Column(name = "ban_start_date")
    private LocalDateTime banStartDate;

    @Column(name = "ban_end_date")
    private LocalDateTime banEndDate;

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false, unique = true)
    @JsonBackReference
    private Shop shop;
}

