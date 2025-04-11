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
public class CartItemUpdatingDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("cart_item_id")
    private Long cartItemId;
}
