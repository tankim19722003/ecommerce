package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse extends ShopBasicInfoResponse{

    @JsonProperty("user_id")
    private long userId;

    @Column(name = "front_cccd_url")
    private String frontCccdUrl;

    @Column(name = "front_cccd_public_id")
    private String frontCccdPublicId;

    @Column(name = "behind_cccd_url")
    private String behindCccdUrl;

    @Column(name = "behind_cccd_public_id")
    private String behindCccdPublicId;
}
