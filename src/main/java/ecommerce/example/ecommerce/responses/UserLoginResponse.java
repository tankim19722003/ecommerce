package ecommerce.example.ecommerce.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse{
    private String token;

    @JsonProperty("user")
    private UserResponse userResponse;
}
