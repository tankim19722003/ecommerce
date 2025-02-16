package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Long> {
}
