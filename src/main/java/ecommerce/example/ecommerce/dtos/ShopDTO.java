package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    @NotBlank(message = "Shop's name can't be blank")
    private String shopName;

    private String description;

    private long villageId;

    private String specificAddress;

    @NotBlank(message = "Phone number name can't be blank")
    private String phoneNumber;

    @NotBlank(message = "Email name can't be blank")
    private String email;

    private MultipartFile cmnd;

}
