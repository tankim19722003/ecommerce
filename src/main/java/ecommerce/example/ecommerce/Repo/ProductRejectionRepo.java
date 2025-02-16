package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductRejection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRejectionRepo extends JpaRepository<ProductRejection, Long> {
}
