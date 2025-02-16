package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
}
