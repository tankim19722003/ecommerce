package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCategoryGroupResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_category_group_name")
    private String productCategoryGroupName;
}
