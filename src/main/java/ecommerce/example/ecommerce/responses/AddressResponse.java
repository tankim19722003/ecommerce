package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    @JsonProperty("province_id")
    private long provinceId;

    @JsonProperty("province_name")
    private String provinceName;

    @JsonProperty("district_id")
    private long districtId;

    @JsonProperty("district_name")
    private String districtName;

    @JsonProperty("village_id")
    private long villageId;

    @JsonProperty("village_name")
    private String villageName;

}
