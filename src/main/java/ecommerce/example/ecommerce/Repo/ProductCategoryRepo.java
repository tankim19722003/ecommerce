package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findByProductId(Long productId);
}
