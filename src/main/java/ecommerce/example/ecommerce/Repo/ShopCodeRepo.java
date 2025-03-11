package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Shop;
import ecommerce.example.ecommerce.models.ShopCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCodeRepo extends JpaRepository<ShopCode, Long> {
}
