package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCategoryTwoLevelDTO {

    @JsonProperty("parent_product_category")
    private String parentProductCategory;

    @JsonProperty("child_product_categories")
    private List<ChildProductCategory> childProductCategories;
}
