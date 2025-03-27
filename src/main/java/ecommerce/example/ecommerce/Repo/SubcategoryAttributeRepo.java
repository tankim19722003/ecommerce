package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.SubCategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryAttributeRepo extends JpaRepository<SubCategoryAttribute, Long> {

    @Query("SELECT s FROM SubCategoryAttribute s WHERE s.subCategory.id = :subcategoryId")
    List<SubCategoryAttribute> findBySubCategoryId(@Param("subcategoryId") Long subcategoryId);

}
