package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductKeywordResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private int price;

    @JsonProperty("rating")
    private float rating;

    @JsonProperty("total_sold")
    private int totalSold;

    @JsonProperty("voucher_responses")
    private List<VoucherResponse> voucherResponses;

    @JsonProperty("product_discount")
    private int productDiscount;

    @JsonProperty("thumbnail_response")
    private ImageResponse thumbnailResponse;
}
