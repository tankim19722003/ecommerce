package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products_rejections")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRejection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rejected_reason", nullable = false, columnDefinition = "TEXT")
    private String rejectedReason;

    @Column(name = "rejected_date", nullable = false, updatable = false)
    private LocalDateTime rejectedDate;

    @PrePersist
    protected void onCreate() {
        rejectedDate = LocalDateTime.now();
    }
}
