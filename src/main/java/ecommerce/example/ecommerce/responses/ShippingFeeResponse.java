package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShippingFeeResponse {

    @JsonProperty("shipping_type_response")
    private ShippingTypeResponse shippingTypeResponse;

    @JsonProperty("price")
    private Float price;
}
