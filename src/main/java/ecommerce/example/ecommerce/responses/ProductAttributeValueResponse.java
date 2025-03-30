package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductAttributeValueResponse {

    @JsonProperty("product_attribute_value_id")
    private Long productAttributeValueId;

    @JsonProperty("attribute_name")
    private String attributeName;

    @JsonProperty("value")
    private  String value;
}
