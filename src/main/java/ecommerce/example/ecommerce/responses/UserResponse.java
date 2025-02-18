package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {

    private Long id;

    private String fullname;

    private String account;

    private String password;

    private String address;

    private String email;

    private Boolean gender;

    @JsonProperty("birth_date")
    private LocalDate birthdate;

    private String avatar;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private List<Role> roles;

}
