package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategoryRequest {
    private Long productId;
    private Long shopId;

    @JsonProperty("product_category_groups")
    private List<ProductCategoryGroupDTO> productCategoryGroups;
}
