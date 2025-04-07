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
public class ProductKeywordPageResponse {
    @JsonProperty("total_page")
    private int totalPage;

    @JsonProperty("product_keyword_responses")
    List<ProductKeywordResponse> productKeywordResponses;

    public void addProductKeywordResponses(List<ProductKeywordResponse> productKeywordResponses) {
        this.productKeywordResponses = productKeywordResponses;
    }
}
