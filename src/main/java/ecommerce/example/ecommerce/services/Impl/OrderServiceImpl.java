package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ShippingTypesRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.Repo.UserVillageRepo;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.models.Order;
import ecommerce.example.ecommerce.models.ShippingType;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserVillage;
import ecommerce.example.ecommerce.responses.OrderResponse;
import ecommerce.example.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserVillageRepo userVillageRepo;

    @Autowired
    private ShippingTypesRepo shippingTypesRepo;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {

        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not found"));

        UserVillage userVillage = userVillageRepo.findById(orderDTO.getUserVillageId())
                .orElseThrow(() -> new RuntimeException("Invalid Address"));

//        ShippingType shippingType = shippingTypesRepo.findById(orderDTO.getShippingTypeId())
//                .orElseThrow(() ->  new RuntimeException("Shipping type does not found"));

        Order order = new Order();
        order.setUser(user);
//        order.setShippingType(shippingType);
        order.setOrderDate(LocalDateTime.now());
        order.setDiscountPercent(order.getDiscountPercent());
        return null;


        // set after save order
//        order.setUserVillageOrder();
    }
}
