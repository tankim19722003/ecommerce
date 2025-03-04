package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("shop_name")
    private String shopName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("address_response")
    private AddressResponse addressResponse;

    @JsonProperty("specific_address")
    private String specificAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("cmnd")
    private String cmnd;
}
