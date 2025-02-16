package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
