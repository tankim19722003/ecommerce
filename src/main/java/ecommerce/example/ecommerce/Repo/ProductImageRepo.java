package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {

    @Query("SELECT COUNT(p) FROM ProductImage p WHERE p.product.id = :productId")
    long countImagesByProductId(@Param("productId") Long productId);

    @Query("SELECT p FROM ProductImage p WHERE p.product.id = :productId")
    List<ProductImage> findByProductId(@Param("productId") Long productId);
}
