package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.SubProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubProductCategoryRepo extends JpaRepository<SubProductCategory, Long> {
    @Query("select sc from SubProductCategory sc where sc.productCategoryGroup.product.id = :productId")
    List<SubProductCategory> getSubcategoryByProductId(@Param("productId") Long productId);
}
