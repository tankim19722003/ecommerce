package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.responses.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(
            OrderDTO orderDTO
    );

}
