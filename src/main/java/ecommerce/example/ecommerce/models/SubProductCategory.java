package ecommerce.example.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sub_product_categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "product_category_group_id")
    private ProductCategoryGroup productCategoryGroup;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subProductCategory")
    private List<OrderDetail> OrderDetails;
}
