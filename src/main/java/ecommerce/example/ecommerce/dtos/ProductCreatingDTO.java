package ecommerce.example.ecommerce.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCreatingDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("category_id")
    private Long categoryId;

    private List<ProductAttributeValueDTO> attributes;
}
