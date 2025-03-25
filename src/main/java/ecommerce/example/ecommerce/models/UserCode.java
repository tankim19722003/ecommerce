package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_codes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private int code;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name ="email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "code_purpose_id")
    private CodePurpose codePurpose;

    @PrePersist
    protected void onCreate() {
        this.dateStart = LocalDateTime.now();
        this.dateEnd = this.dateStart.plusMinutes(2);
        this.active = false;
    }
}
