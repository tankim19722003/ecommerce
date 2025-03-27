package ecommerce.example.ecommerce.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryAttributeResponse extends AttributeSubIdResponse{

    @JsonProperty("subcategory_id")
    private Long subcategoryId;

    @JsonProperty("subcategory_value")
    private String subcategoryValue;
}
