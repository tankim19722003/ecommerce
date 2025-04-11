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
public class CartItemResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("price")
    private int price;

    @JsonProperty("discount_percent")
    private int discountPercent;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("stock_quantity")
    private int stockQuantity;

    @JsonProperty("voucher_responses")
    private List<VoucherResponse> voucherResponses;
}
