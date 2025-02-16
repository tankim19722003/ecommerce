package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepo extends JpaRepository<Size, Long> {
}
