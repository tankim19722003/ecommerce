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
public class MultipleProductCategoryDTO {

    @JsonProperty("product_category_group")
    private String productCategoryGroup;

    @JsonProperty("sub_product_category_group")
    private String subProductCategoryGroup;

    @JsonProperty("product_category_two_level")
    List<ProductCategoryTwoLevelDTO> ProductCategoryTwoLevelResponse;

}
