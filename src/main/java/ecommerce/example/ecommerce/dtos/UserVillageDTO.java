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
public class UserVillageDTO {

    @JsonProperty("village_id")
    private long villageId;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("phone_number")
    private String phoneNumber;


    @JsonProperty("specific_address")
    private String specificAddress;
}
