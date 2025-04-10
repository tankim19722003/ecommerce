package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductShippingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductShippingTypeRepo extends JpaRepository<ProductShippingType, Long> {
}
