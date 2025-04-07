package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCategoryOneLevelList implements ProductCategoryResponse{
    @JsonProperty("product_category_one_level_responses")
    List<ProductCategoryOneLevelResponse> productCategoryOneLevelResponses;

    public void addProductCategoryOneLevelResponse(ProductCategoryOneLevelResponse productCategoryOneLevelResponse) {
        if (productCategoryOneLevelResponses == null)
            productCategoryOneLevelResponses = new ArrayList<>();
        productCategoryOneLevelResponses.add(productCategoryOneLevelResponse);
    }
}
