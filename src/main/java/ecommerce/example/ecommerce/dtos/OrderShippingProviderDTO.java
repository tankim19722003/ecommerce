package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderShippingProviderDTO {

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("shipping_provider_id")
    private Long shippingProviderId;

    @JsonProperty("order_id")
    private Long orderId;
}
