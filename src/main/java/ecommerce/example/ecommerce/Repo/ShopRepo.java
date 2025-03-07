package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends JpaRepository<Shop, Long> {
    boolean existsByShopName(String shopName);
    boolean existsByIdAndUserId(Long shopId, Long userId);

    Optional<Shop> findByUserId(long userId);

    List<Shop> findByStatus(String status);
}
