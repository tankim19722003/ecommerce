package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.OrderDTO;
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

}
