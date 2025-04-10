package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductShippingInfoDTO extends ShippingFeeDTO{

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("shipping_type_ids")
    private List<Long> shippingTypeIds;

}
