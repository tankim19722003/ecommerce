package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderDetailDTO;
import ecommerce.example.ecommerce.models.*;
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
    private ShippingTypeRepo shippingTypesRepo;

    @Autowired
    private ProductShippingTypeRepo productShippingTypeRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserVillageOrderRepo userVillageOrderRepo;


    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {

        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not found"));

        UserVillage userVillage = userVillageRepo.findById(orderDTO.getUserVillageId())
                .orElseThrow(() -> new RuntimeException("Invalid Address"));

        ProductShippingType productShippingType = productShippingTypeRepo.findById(orderDTO.getProductShippingTypeId())
                .orElseThrow(() -> new RuntimeException("Product shipping type does not found"));



        // create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.PENDING);
        order.setProductShippingType(productShippingType);
        order.setPhoneNumber(userVillage.getPhoneNumber());
        order.setReceiverName(userVillage.getReceiverName());
        order.setNotes(order.getNotes());
        order.setExpectedReceiveDate(LocalDateTime.now().plusDays(productShippingType.getShippingType().getEstimatedTime()));
        order.setOrderDate(LocalDateTime.now());
        order.setDiscountPercent(order.getDiscountPercent());

        orderRepo.save(order);

        // create address for order
        UserVillageOrder userVillageOrder = new UserVillageOrder();
        userVillageOrder.setOrder(order);
        userVillageOrder.setVillage(userVillage.getVillage());
        userVillageOrder.setSpecificAddress(userVillageOrder.getSpecificAddress());

        userVillageOrderRepo.save(userVillageOrder);

        // save order detail
//        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetailDTOs()) {
//
//        }



        return null;


        // set after save order
//        order.setUserVillageOrder();
    }
}
