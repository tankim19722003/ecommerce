package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductCategoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryGroupRepo extends JpaRepository<ProductCategoryGroup, Long> {
}
