package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("product_category_id")
    private Long productCategoryId;

    @JsonProperty("product_subcategory_id")
    private Long productSubcategoryId;
}
