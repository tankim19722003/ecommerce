package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

//    @Query("select p from Product p where p.name like %:keyword% or p.description like %:keyword%")
//    Page<Product> getProductsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> getProductsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p.id FROM Product p WHERE p.shop.id = :shopId ORDER BY p.createdAt ASC")
    List<Long> getProductIdsByShopId(@Param("shopId") Long shopId);


}
