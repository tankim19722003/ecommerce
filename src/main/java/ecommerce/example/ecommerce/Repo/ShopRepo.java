package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopRepo extends JpaRepository<Shop, Long> {
    boolean existsByShopName(String shopName);
    boolean existsByIdAndUserId(Long shopId, Long userId);

    Optional<Shop> findByUserId(long userId);

    Boolean existsByEmail(String email);

}
