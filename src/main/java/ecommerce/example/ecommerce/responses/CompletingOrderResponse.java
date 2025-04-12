package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompletingOrderResponse {

    @JsonProperty("order_details_responses")
    private List<OrderDetailResponse> orderDetailResponses;

    @JsonProperty("order_id")
    private Long orderId;

    private int orderPrice;

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("shop_name")
    private String shopName;
}
