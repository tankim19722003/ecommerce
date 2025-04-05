package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Float rating;

    @JsonProperty("voucher_responses")
    private  ProductDiscountResponse discountResponse;

    @JsonProperty("image_responses")
    List<ImageResponse> imageResponses;

    public void addImageResponses(List<ImageResponse> imageResponses) {
        this.imageResponses = imageResponses;
    }

    public void addImageResponse(ImageResponse imageResponse) {
        if (imageResponses.isEmpty())
            imageResponses = new ArrayList<>();

        imageResponses.add(imageResponse);
    }
}
