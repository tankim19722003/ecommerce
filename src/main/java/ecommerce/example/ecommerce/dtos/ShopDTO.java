package ecommerce.example.ecommerce.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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

    private MultipartFile frontCccd;

    private MultipartFile behindCccd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
