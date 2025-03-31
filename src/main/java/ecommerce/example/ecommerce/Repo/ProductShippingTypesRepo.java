package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductShippingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductShippingTypesRepo extends JpaRepository<ProductShippingType, Long> {

    @Query("select pst from ProductShippingType pst where pst.product.id = :productId")
    List<ProductShippingType> findByProductShippingTypeByProductId(@Param("productId") Long productId);
}
