package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.Voucher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRatingOrderResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("total_sold")
    private Integer totalSold;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("discount_responses")
    private  ProductDiscountResponse discountResponse;

//    @JsonProperty("image_responses")
//    List<ImageResponse> imageResponses;
    @JsonProperty("thumbnail")
    private ImageResponse thumbnail;

    @JsonProperty("voucher_responses")
    private List<VoucherResponse> voucherResponses;

    public void addVoucherResponse(List<VoucherResponse> voucherResponses) {
        this.voucherResponses = voucherResponses;
    }

}
