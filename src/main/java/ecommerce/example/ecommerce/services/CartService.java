package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.CartDTO;
import ecommerce.example.ecommerce.dtos.CartItemUpdatingDTO;
import ecommerce.example.ecommerce.responses.CartItemResponse;
import ecommerce.example.ecommerce.responses.ShopCartItemResponse;

import java.util.List;

public interface CartService {

    void  addProductToCart(CartDTO cartDTO);

    List<ShopCartItemResponse> getAllCartItems(Long userId);

    CartItemResponse updateQuantityOfCartItem(CartItemUpdatingDTO cartItemUpdatingDTO);

    void deleteProductOutOfCart(Long userId, Long cartItemId);
}
