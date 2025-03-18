package ecommerce.example.ecommerce.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoUpdating {

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("account")
    private String account;

//    @JsonProperty("email")
//    private String email;
//
//    @JsonProperty("phone_number")
//    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("gender")
    private Boolean gender;

    @JsonProperty("birth_date")
    private Date birthdate;

//    @JsonProperty("avatar")
//    private String avatar;
}
