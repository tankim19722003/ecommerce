package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("total_money")
    private float totalMoney;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("note")
    private String note;
//
//    @JsonProperty("created_at")
//    private LocalDateTime createdAt;
//
//    @JsonProperty("updated_at")
//    private LocalDateTime updatedAt;

    @JsonProperty("voucher_response")
    private VoucherResponse voucherResponse;

    @JsonProperty("shipping_type_response")
    private ShippingTypeResponse shippingTypeResponse;

    @JsonProperty("user_village_response")
    private AddressResponse userVillageResponse;

    @JsonProperty("order_detail_responses")
    private List<OrderDetailResponse> orderDetailResponses;

    public void addOrderDetailResponse(OrderDetailResponse orderDetailResponse) {
        if (orderDetailResponses == null)
            orderDetailResponses = new ArrayList<>();

        orderDetailResponses.add(orderDetailResponse);
    }
}
