package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreatingResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("subcategory_id")
    private Long subcategoryId;

    @JsonProperty("subcategory_name")
    private String subcategoryName;

    @JsonProperty("thumbnail")
    private ImageResponse thumbnail;

    @JsonProperty("product_images")
    private List<ImageResponse> productImages;


    public void addProductImage(ImageResponse imageResponse) {
        if (productImages == null)
            productImages = new ArrayList<>();
        productImages.add(imageResponse);
    }
}
