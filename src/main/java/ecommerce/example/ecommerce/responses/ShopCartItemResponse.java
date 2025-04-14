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
public class ShopCartItemResponse {

    @JsonProperty("shop_info_response")
    private ShopBasicInfoResponse shopBasicInfoResponse;

    @JsonProperty("cart_item_response")
    private List<CartItemResponse> cartItemResponses;

    public void addCartItem(CartItemResponse cartItemResponse) {
        if (cartItemResponses == null)
            cartItemResponses = new ArrayList<>();

        cartItemResponses.add(cartItemResponse);
    }

}
