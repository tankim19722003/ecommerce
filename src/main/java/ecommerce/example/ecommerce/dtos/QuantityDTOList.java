package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuantityDTOList {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("quantity_list")
    List<QuantityDTO> quantityDTOList;
}
