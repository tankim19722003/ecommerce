package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ShopBanned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopBannedRepo extends JpaRepository<ShopBanned, Long> {
}
