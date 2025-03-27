package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubcategoryAttributeListResponse {

    @JsonProperty("subcategory_id")
    private Long subcategoryId;

    @JsonProperty("subcategory_value")
    private String subcategoryValue;

    @JsonProperty("sub_attribute")
    List<AttributeSubIdResponse> attributeSubIdResponses;

    public void addAttributeSubIdResponse(AttributeSubIdResponse attributeSubIdResponse) {
        if (attributeSubIdResponses == null)  attributeSubIdResponses = new ArrayList<>();
            attributeSubIdResponses.add(attributeSubIdResponse);
    }
}
