package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDetailResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private int price;

    @JsonProperty("discount_percent")
    private int discountPercent;

    // category name, image, id
    @JsonProperty("product_category_id")
    private Long productCategoryId;

    @JsonProperty("product_category_name")
    private String productCategoryName;

    @JsonProperty("product_category_image_url")
    private String productCategoryImageUrl;

    @JsonProperty("product_sub_category_id")
    private Long productSubCategoryId;

    @JsonProperty("product_sub_category_name")
    private String productSubCategoryName;

}
