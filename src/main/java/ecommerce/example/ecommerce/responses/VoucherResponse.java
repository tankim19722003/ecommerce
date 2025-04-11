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
public class VoucherResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private int code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("discount_percent")
    private int discountPercent;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("minimum_order_value")
    private Integer minimumOrderValue;

}
