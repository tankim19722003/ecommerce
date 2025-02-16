package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Long> {
}
