package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductImageListResponse {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_image_list")
    private List<ProductImageResponseInList> productImageResponseInLists;
}
