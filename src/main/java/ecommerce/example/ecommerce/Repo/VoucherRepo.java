package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VoucherRepo extends JpaRepository<ProductDiscount, Long> {
}
