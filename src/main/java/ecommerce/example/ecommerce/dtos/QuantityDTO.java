package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuantityDTO {

    @JsonProperty("first_product_attribute_value_id")
    private long firstProductAttributeValueId;

    @JsonProperty("second_product_attribute_value_id")
    private long secondProductAttributeValueId;

    @JsonProperty("quantity")
    private int quantity;
}
