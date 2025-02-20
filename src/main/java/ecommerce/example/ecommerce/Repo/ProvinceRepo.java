package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<Province, Long> {
}
