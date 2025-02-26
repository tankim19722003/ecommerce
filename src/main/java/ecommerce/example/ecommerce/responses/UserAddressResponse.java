package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressResponse extends AddressResponse{

    @JsonProperty("address_id")
    private long addressId;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("specific_village")
    private String specificVillage;

    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
