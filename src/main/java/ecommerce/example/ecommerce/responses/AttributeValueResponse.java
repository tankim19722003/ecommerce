package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeValueResponse {

    @JsonProperty("product_attribute_value_id")
    private Long productAttributeValueId;

    @JsonProperty("attribute_id")
    private Long attributeId;

    @JsonProperty("attribute_name")
    private String attributeName;

    @JsonProperty("value")
    private String value;

    @JsonProperty("image_id")
    private Long imageId;

    @JsonProperty("public_id")
    private String publicId ;

    @JsonProperty("image_url")
    private String imageUrl;
}
