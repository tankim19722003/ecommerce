package ecommerce.example.ecommerce.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressListResponse {

    private long userId;

    private List<UserVillageResponse> addressResponses;

}
