//package ecommerce.example.ecommerce.Repo;
//
//import ecommerce.example.ecommerce.models.ProductAttributeValue;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ProductAttributeValueRepo extends JpaRepository<ProductAttributeValue, Long> {
//
//    @Query("SELECT pav FROM ProductAttributeValue pav WHERE pav.product.id = :productId")
//    List<ProductAttributeValue> findAllByProductId(@Param("productId") Long productId);
//}
