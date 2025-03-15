package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImageResponse {

    @JsonProperty("public_id")
    private String publicId;

    @JsonProperty("avatar_url")
    private String avatarUrl;
}
