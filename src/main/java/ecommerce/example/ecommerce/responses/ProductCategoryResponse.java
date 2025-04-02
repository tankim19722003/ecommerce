package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryResponse {

    private Long id;

    @JsonProperty("first_category")
    private String firstCategory;

    @JsonProperty("second_category")
    private String secondCategory;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private int price;

    @JsonProperty("public_id")
    private String publicId;

    @JsonProperty("image_url")
    private String imageUrl;
}
