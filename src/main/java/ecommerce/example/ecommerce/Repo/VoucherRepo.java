package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VoucherRepo extends JpaRepository<Voucher, Long> {

    @Query("SELECT v FROM Voucher v WHERE v.shop.id = :shopId AND v.startDate <= :currentTime AND v.endDate > :currentTime")
    List<Voucher> findValidVouchersByProductId(
            @Param("shopId") Long shopId,
            @Param("currentTime") LocalDateTime currentTime
    );

}
