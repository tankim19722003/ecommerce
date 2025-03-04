package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeValueRepo extends JpaRepository<ProductAttributeValue, Long> {
}
