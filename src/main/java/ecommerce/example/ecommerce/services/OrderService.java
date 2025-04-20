package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderShippingProviderDTO;
import ecommerce.example.ecommerce.responses.CompletingOrderResponse;
import ecommerce.example.ecommerce.responses.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(
            OrderDTO orderDTO
    );

    List<CompletingOrderResponse> getOrderByStatus(
            Long userId, String status
    );

    void cancelOrder(
            Long userId, Long orderId
    );

    List <OrderResponse> getOrderByShopIdAndStatus(Long shopId, String status);

    void changeOrderStatusToPackagingStatus(Long shopId, Long orderId);

    void changeOrderStatus(Long shopId, Long orderId, String status);

    void addShippingProviderToOrder(
            OrderShippingProviderDTO orderShippingProvider
    );

    List<OrderResponse> getAllOrderByShippingProviderIdAndOrderStatus(
            Long shippingProviderId,
            String status
    );
}
