package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.CartDTO;
import ecommerce.example.ecommerce.dtos.CartItemUpdatingDTO;
import ecommerce.example.ecommerce.responses.CartItemResponse;

import java.util.List;

public interface CartService {

    void  addProductToCart(CartDTO cartDTO);

    List<CartItemResponse> getAllCartItems(Long userId);

    CartItemResponse updateQuantityOfCartItem(CartItemUpdatingDTO cartItemUpdatingDTO);

    void deleteProductOutOfCart(Long userId, Long productId);
}
