package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryGroupDTO {

    @JsonProperty("product_category_group_name")
    private String name;

    @JsonProperty("product_categories")
    List<ProductCategoryDTO> productCategoryDTOs;
}
