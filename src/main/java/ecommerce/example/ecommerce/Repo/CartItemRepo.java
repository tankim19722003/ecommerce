package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);
}
