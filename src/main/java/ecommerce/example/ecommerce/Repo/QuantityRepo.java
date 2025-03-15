package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuantityRepo extends JpaRepository<Quantity, Long> {

    @Query("SELECT q FROM Quantity q WHERE q.product.id = :productId")
    List<Quantity> findAllByProductId(@Param("productId") Long productId);
}
