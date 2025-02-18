package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLoginDTO {

    @JsonProperty("account")
    @NotBlank(message = "Account can't be empty")
    private String account;

    @JsonProperty("password")
    @NotBlank(message = "Message can't be empty")
    private String password;

}
