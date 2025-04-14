package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.ShippingType;
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

    @JsonProperty("shipping_type_responses")
    private List<ShippingTypeResponse> shippingTypeResponses;

//    public void addShippingTypeResponse(List<ShippingTypeResponse> shippingTypeResponses) {
//        this.shippingTypeResponses =
//    }
    private void addShippingType(ShippingTypeResponse shippingTypeResponse) {
        if (shippingTypeResponses == null)
            shippingTypeResponses = new ArrayList<>();

        shippingTypeResponses.add(shippingTypeResponse);
    }

    public void addCartItem(CartItemResponse cartItemResponse) {
        if (cartItemResponses == null)
            cartItemResponses = new ArrayList<>();

        cartItemResponses.add(cartItemResponse);
    }

}
