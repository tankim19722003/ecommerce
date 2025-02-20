package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, Long> {
}
