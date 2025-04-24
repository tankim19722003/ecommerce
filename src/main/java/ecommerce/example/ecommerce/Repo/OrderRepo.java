package ecommerce.example.ecommerce.Repo;

import ecommerce.example.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id = :userId and o.status = :status")
    List<Order> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);

    @Query("select o from Order o where o.shop.id = :shopId and o.status = :status")
    List<Order> findAllByShopIdAndStatus(@Param("shopId") Long shopId,@Param("status") String status);

    @Query("select o from Order o where o.shippingProvider.id = :shippingProviderId and o.status = :status")
    List<Order> findAllByShippingProviderIdAndStatus(
            @Param("shippingProviderId") Long shippingProviderId,
            @Param("status") String status
    );

    @Query("select o.shop.id from Order o where o.id = :orderId")
    Long getShopIdByOrderId(Long orderId);
}
