package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.dtos.ProductCategoryGroupResponse;
import ecommerce.example.ecommerce.dtos.ProductCategoryTwoLevelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MultipleProductCategoryResponse {

    @JsonProperty("product_category_group")
    private ProductCategoryGroupResponse productCategoryGroup;

    @JsonProperty("sub_product_category_group")
    private ProductCategoryGroupResponse subProductCategoryGroup;

    @JsonProperty("product_category_two_level")
    List<ProductCategoryTwoLevelResponse> ProductCategoryTwoLevelResponse;

}
