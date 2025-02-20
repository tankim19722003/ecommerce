package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepo extends JpaRepository<Village, Long> {
}
