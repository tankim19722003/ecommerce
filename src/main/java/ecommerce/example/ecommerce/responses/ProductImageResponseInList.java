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
public class ProductImageResponseInList {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("public_id")
    private String publicId ;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("attribute_value_id")
    private Long attributeValueId;

    @JsonProperty("attribute_id")
    private Long attributeId;

    @JsonProperty("attribute_name")
    private String attributeName;

    @JsonProperty("attribute_value")
    private String attributeValue;

}
