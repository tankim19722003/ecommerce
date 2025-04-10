package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductShippingInfoDTO extends ShippingFeeDTO{

    @JsonProperty("shop_id")
    private int shopId;

    @JsonProperty("product_id")
    private int productId;

}
