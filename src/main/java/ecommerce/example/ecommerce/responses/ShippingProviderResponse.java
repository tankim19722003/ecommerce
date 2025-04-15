package ecommerce.example.ecommerce.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingProviderResponse {

    private Long id;

    private String account;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private String phone;

    private String email;

    private String status;

    private String name;
}
