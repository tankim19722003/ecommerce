package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    @JsonProperty("shop_name")
    private String shopName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("address_id")
    private String addressId;

    @JsonProperty("specific_address")
    private String specificAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

}
