package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.models.Role;
import ecommerce.example.ecommerce.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse implements IUserResponse{

    private Long id;

    private String fullname;

    private String account;

//    private String address;

    private String email;

    private Boolean gender;

    @JsonProperty("birth_date")
    private Date birthdate;

    private String avatar;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private List<String> roles;

    public void addRole(String role) {
        if (roles == null) roles = new ArrayList<>();
        roles.add(role);
    }


}
