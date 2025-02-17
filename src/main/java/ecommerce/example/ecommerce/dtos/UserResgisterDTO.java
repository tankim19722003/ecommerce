package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResgisterDTO {

    @JsonProperty("email")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number can't be epty")
    private String phoneNumber;

    @JsonProperty("password")
    @NotBlank(message = "Password can't be epty")
    private String password;

}
