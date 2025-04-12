package ecommerce.example.ecommerce.responses.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.responses.VoucherResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestVoucher extends VoucherResponse {
    @JsonProperty("shop_id")
    private Long shopId;

}
