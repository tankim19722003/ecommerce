package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.SubProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartItemResponse {

    @JsonProperty("cart_item_id")
    private Long cartItemId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("price")
    private int price;

    @JsonProperty("discount_percent")
    private int discountPercent;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("stock_quantity")
    private int stockQuantity;

    @JsonProperty("product_category_id")
    private Long productCategoryId;

    @JsonProperty("product_category_name")
    private String productCategoryName;

    @JsonProperty("product_category_image")
    private ImageResponse productCategoryImage;

    @JsonProperty("subcategory_id")
    private Long subcategoryId;

    @JsonProperty("subcategory_name")
    private String subcategoryName;

    @JsonProperty("voucher_responses")
    private List<VoucherResponse> voucherResponses;
}
