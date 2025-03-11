package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResgisterDTO {

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number can't be epty")
    private String account;

    @JsonProperty("password")
    @NotBlank(message = "Password can't be epty")
    private String password;

}
