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
public class SubCategoryDTO {

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


}
