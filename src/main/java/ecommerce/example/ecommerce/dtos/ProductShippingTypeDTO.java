package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductShippingTypeDTO {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("shipping_type_ids")
    private List<Long> shippingTypeIds;
}
