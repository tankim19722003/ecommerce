package ecommerce.example.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartItemUpdatingDTO {
    private Long userId;
    private Long productId;
    private int quantity;
}
