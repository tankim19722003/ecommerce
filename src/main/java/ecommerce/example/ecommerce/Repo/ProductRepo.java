package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
