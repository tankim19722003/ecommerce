package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name like %:keyword% or p.description like %:keyword%")
    List<Product> getProductsByKeyword(@Param("keyword") String keyword);
}
