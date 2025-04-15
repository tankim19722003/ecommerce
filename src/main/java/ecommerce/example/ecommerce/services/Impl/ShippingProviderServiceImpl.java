package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.OrderRepo;
import ecommerce.example.ecommerce.Repo.ShippingProviderRepo;
import ecommerce.example.ecommerce.dtos.UpdatingShippingProviderOrderStatusDTO;
import ecommerce.example.ecommerce.models.Order;
import ecommerce.example.ecommerce.models.ShippingProvider;
import ecommerce.example.ecommerce.responses.ShippingProviderResponse;
import ecommerce.example.ecommerce.services.ShippingProviderSerivice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingProviderServiceImpl implements ShippingProviderSerivice {

    @Autowired
    private ShippingProviderRepo shippingProviderRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ShippingProviderResponse> getAllShippingProvider() {
        List<ShippingProvider> shippingProviders = shippingProviderRepo.findAll();

        return shippingProviders.stream()
                .map(shippingProvider -> shippingProvider.toShippingProviderResponse())
                .toList();
    }

    @Override
    public void changeOrderStatusByShippingProvider(
            UpdatingShippingProviderOrderStatusDTO updatingShippingProviderOrderStatusDTO
    ) {

        Order order = orderRepo.findById(updatingShippingProviderOrderStatusDTO.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order does not found"));

        if (order.getShippingProvider().getId() != updatingShippingProviderOrderStatusDTO.getShippingProviderId())
            throw new RuntimeException("You are not allowed to modify this order");

        String status = updatingShippingProviderOrderStatusDTO.getStatus().toUpperCase();
        if (status.equals(Order.SHIPPING) ||
                status.equals(Order.COMPLETED) ||
                status.equals(Order.RETURNING)
        ) {
            order.setStatus(status);
            orderRepo.save(order);
        } else {
            throw new RuntimeException("Your status is not allowed");
        }
    }
}
