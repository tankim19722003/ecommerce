package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ShippingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingTypeRepo extends JpaRepository<ShippingType, Long> {
}
