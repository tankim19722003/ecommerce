package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedBackRepo extends JpaRepository<Feedback, Long> {

    @Query("select fb from Feedback fb where fb.orderDetail.product.id = :productId")
    List<Feedback> findAllByProductId(Long productId);

    @Query("select avg(f.rating) from Feedback f where f.orderDetail.product.id = :productId")
    Double getRating(@Param("productId") Long productId);
}
