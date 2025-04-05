package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Long> {
    Optional<ProductDiscount> findByDateStartLessThanEqualAndDateEndGreaterThanEqual(LocalDateTime start, LocalDateTime end);
}
