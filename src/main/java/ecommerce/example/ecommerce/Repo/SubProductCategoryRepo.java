package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.SubProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubProductCategoryRepo extends JpaRepository<SubProductCategory, Long> {
}
