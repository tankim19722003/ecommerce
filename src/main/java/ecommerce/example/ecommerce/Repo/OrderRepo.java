package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
