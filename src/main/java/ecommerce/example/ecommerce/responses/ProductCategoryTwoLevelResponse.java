package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryTwoLevelResponse {

    @JsonProperty("product_category_response")
    private ParentProductCategoryResponse productCategoryResponse;

    @JsonProperty("child_product_category_responses")
    private List<ChildProductCategoryResponse> childProductCategoryResponses;
}
