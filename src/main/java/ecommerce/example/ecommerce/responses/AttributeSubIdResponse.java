package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeSubIdResponse {
    @JsonProperty("subcategory_attribute_id")
    private Long subcategoryAttributeId;

    @JsonProperty("attribute_id")
    private Long attributeId;

    @JsonProperty("attribute_value")
    private String attributeValue;


}
