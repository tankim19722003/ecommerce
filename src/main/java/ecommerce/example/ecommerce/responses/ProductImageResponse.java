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
public class ProductImageResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("public_id")
    private String publicId ;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("attribute_value_id")
    private Long attributeValueId;

    private Long attributeId;

    private String attributeName;

    private String attributeValue;


}
