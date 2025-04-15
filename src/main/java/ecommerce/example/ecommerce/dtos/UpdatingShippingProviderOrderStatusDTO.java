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
public class UpdatingShippingProviderOrderStatusDTO {

    @JsonProperty("shipping_provider_id")
    private Long shippingProviderId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("status")
    private String status;
}
