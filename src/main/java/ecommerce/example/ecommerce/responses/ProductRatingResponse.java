package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;

public class ProductRatingResponse {

    @JsonProperty("id")
    private Long id ;

    @JsonProperty("total_sold")
    private int totalSold;

    @JsonProperty("rating")
    private Float rating;

    @JsonProperty("voucher_response")
    private VoucherResponse voucherResponse;

    @JsonProperty("thumbnail")
    private ImageResponse thumbnail;
}
