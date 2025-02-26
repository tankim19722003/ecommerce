package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserVillageResponse extends AddressResponse{
    @JsonProperty("address_id")
    private long addressId;

    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("specific_address")
    private String specificAddress;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
