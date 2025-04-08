package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoucherUpdatingDTO extends VoucherDTO{

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("voucher_id")
    private Long voucherId;
}
