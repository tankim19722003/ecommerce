package ecommerce.example.ecommerce.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    private List<SubCategory> subCategories;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now(); // Set created_at to the current date
    }


    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDate.now();  // Set updated_at to the current date
    }
}