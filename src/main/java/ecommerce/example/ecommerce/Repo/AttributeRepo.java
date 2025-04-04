package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.responses.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

    @Query("SELECT ca.attribute FROM SubCategoryAttribute ca WHERE ca.subCategory.id = :categoryId")
    List<Attribute> findAttributesByCategoryId(@Param("categoryId") Long categoryId);

    Boolean existsByName(String name);

}
