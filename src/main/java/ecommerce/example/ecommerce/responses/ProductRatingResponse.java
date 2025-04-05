package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRatingResponse {

    @JsonProperty("id")
    private Long id ;

    @JsonProperty("total_sold")
    private int totalSold;

    @JsonProperty("rating")
    private Float rating;

    @JsonProperty("voucher_response")
    private DiscountResponse voucherResponse;

    @JsonProperty("thumbnail")
    private ImageResponse thumbnail;
}
