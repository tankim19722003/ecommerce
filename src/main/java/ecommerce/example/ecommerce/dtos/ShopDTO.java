package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Shop's name can't be blank")
    private String shopName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("village_id")
    private long villageId;

    @JsonProperty("specific_address")
    private String specificAddress;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number name can't be blank")
    private String phoneNumber;

    @JsonProperty("email")
    @NotBlank(message = "Email name can't be blank")
    private String email;

}
