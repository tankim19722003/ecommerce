package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepo extends JpaRepository<ProductSize, Long> {
}
