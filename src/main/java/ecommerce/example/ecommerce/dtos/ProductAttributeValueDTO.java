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
public class ProductAttributeValueDTO {

    @JsonProperty("subcategory_attribute_id")
    private Long subcategoryAttributeId;

    @JsonProperty("value")
    private String value;
}
