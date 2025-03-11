package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.SubCategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryAttributeRepo extends JpaRepository<SubCategoryAttribute, Long> {

}
