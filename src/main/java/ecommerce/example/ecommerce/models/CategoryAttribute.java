package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category_attributes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private SubCategory subCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryAttribute")
    private List<ProductAttributeValue> productAttributeValues;
}
