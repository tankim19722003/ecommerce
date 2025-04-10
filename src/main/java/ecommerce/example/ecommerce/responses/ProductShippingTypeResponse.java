package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductShippingTypeResponse {

    @JsonProperty("shipping_types")
    private List<ShippingTypeResponse> shippingTypeResponses;

    public void addShippingType(ShippingTypeResponse shippingTypeResponse) {
        if (shippingTypeResponses == null)
            shippingTypeResponses = new ArrayList<>();

        shippingTypeResponses.add(shippingTypeResponse);
    }
}
