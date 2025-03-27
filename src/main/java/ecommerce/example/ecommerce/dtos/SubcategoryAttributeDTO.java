package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubcategoryAttributeDTO {

    @JsonProperty("subcategory_id")
    private Long subcategoryId;

    @JsonProperty("attribute_id")
    private Long attributeId;

}
