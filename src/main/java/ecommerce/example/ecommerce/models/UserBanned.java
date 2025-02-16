package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_banned")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBanned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ban_reason", nullable = false, columnDefinition = "TEXT")
    private String banReason;

    @Column(name = "ban_start_date", nullable = false)
    private LocalDateTime banStartDate;

    @Column(name = "ban_end_date", nullable = false)
    private LocalDateTime banEndDate;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
