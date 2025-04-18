package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("user_address_id")
    private Long userVillageId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_shipping_type_id")
    private Long productShippingTypeId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("voucherId")
    private Long voucherId;

    @JsonProperty("note")
    private String note;

    @JsonProperty("order_detail_dtos")
    private List<OrderDetailDTO> orderDetailDTOs;


}
