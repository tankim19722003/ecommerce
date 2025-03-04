package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

    @Query("SELECT ca.attribute FROM CategoryAttribute ca WHERE ca.category.id = :categoryId")
    List<Attribute> findAttributesByCategoryId(@Param("categoryId") Long categoryId);
}
