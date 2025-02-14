package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UserBanned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_banned_users"))
    private User user;

    @Column(name = "ban_reason", nullable = false, columnDefinition = "TEXT")
    private String banReason;

    @Column(name = "ban_start_date", nullable = false)
    private LocalDateTime banStartDate;

    @Column(name = "ban_end_date", nullable = false)
    private LocalDateTime banEndDate;
}
