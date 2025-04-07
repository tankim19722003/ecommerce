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
public class ProductRatingOrderPageResponse {

    @JsonProperty("total_page")
    private int totalPage;

    private List<ProductRatingOrderResponse> productRatingOrderResponses;

    public void addProductRatingOrderResponses(List<ProductRatingOrderResponse> productRatingOrderResponses) {
        this.productRatingOrderResponses = productRatingOrderResponses;
    }
}
