package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    @Query("SELECT s FROM SubCategory s WHERE s.category.id = :categoryId")
    List<SubCategory> getSubCategoriesByCategoryId(@Param("categoryId") Long categoryId);
}
