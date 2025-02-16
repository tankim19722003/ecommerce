package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
