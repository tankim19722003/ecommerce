package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

//    @Query("select avg(od.rating) from OrderDetail od where od.product.id = :productId")
//    Double getRating(@Param("productId") Long productId);

}
