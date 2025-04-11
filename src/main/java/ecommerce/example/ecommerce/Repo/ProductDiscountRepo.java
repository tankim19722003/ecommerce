package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Long> {
    Optional<ProductDiscount> findByDateStartLessThanEqualAndDateEndGreaterThanEqual(LocalDateTime start, LocalDateTime end);

    @Query("select pd from ProductDiscount pd where pd.product.id = :productId AND pd.dateStart <= :now AND pd.dateEnd > :now")
    Optional<ProductDiscount> findByProductId(@Param("productId") Long productId, @Param("now") LocalDateTime now);
}
