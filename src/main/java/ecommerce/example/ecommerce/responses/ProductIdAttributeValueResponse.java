package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class ProductIdAttributeValueResponse extends ProductAttributeValueResponse{
    @JsonProperty("product_id")
    private int productId;
}
