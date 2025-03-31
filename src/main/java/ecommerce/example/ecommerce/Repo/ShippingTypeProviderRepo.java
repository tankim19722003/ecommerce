package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ShippingProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingTypeProviderRepo extends JpaRepository<ShippingProvider, Long> {
}
