package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
