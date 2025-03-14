package ecommerce.example.ecommerce.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuantityResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("first_attribute_value")
    private AttributeValueResponse firstAttributeValue;

    @JsonProperty("second_attribute_value")
    private AttributeValueResponse secondAttributeValue;

    @JsonProperty("quantity")
    private int quantity;
}
