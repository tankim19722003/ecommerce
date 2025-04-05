    package ecommerce.example.ecommerce.Repo;

    import ecommerce.example.ecommerce.models.Voucher;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface VoucherRepo extends JpaRepository<Voucher, Long> {

        @Query("SELECT v FROM Voucher v WHERE v.product.id = :productId AND v.endDate > CURRENT_TIMESTAMP AND v.startDate <= CURRENT_TIMESTAMP")
        List<Voucher> findVoucherByProductId(@Param("shopId") Long shopId);
    }
