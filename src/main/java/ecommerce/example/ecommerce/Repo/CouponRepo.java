package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
}
