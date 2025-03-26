package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {
    @JsonProperty("first_category")
    private String firstCategory;

    @JsonProperty("second_category")
    private String secondCategory;

    @JsonProperty("quantity")
    private int quantity;
}
