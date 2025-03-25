package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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


    @JsonProperty("user_id")
    private long userId;

    @Column(name = "front_cccd_url")
    private String frontCccdUrl;

    @Column(name = "front_cccd_public_id")
    private String frontCccdPublicId;

    @Column(name = "behind_cccd_url")
    private String behindCccdUrl;

    @Column(name = "behind_cccd_public_id")
    private String behindCccdPublicId;
}
