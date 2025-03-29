package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

//    @Query("SELECT pc FROM ProductCategory pc WHERE pc.product.id = :productId")
//    List<ProductCategory> findByProductId(@Param("productId") Long productId);

}
