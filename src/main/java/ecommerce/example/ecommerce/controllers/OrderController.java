package ecommerce.example.ecommerce.controllers;


import ecommerce.example.ecommerce.Repo.ShopRepo;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderShippingProviderDTO;
import ecommerce.example.ecommerce.models.Order;
import ecommerce.example.ecommerce.responses.CompletingOrderResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.OrderResponse;
import ecommerce.example.ecommerce.services.Impl.OwnerService;
import ecommerce.example.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ShopRepo shopRepo;

    @PostMapping()
    public ResponseEntity<?> createOrder(
            @RequestBody OrderDTO orderDTO
    ) {

        try {
            ownerService.checkValidUser(orderDTO.getUserId());
            OrderResponse orderResponse = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(orderResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/get_order_by_user_id_and_status")
    public ResponseEntity<?> getOrderByUserIdAndStatus(
        @RequestParam("userId") Long userId,
        @RequestParam("status") String status

    ) {

        try {
            ownerService.checkValidUser(userId);
            List<CompletingOrderResponse> orderResponses = orderService.getOrderByStatus(userId, status);
            orderService.getOrderByStatus(userId, status);
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/cancel_order")
    public ResponseEntity<?> cancelOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidUser(userId);
            orderService.cancelOrder(userId, orderId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }


    @GetMapping("/get_shop_order_by_status")
    public ResponseEntity<?> getShopOrderByShopIdAndStatus(
            @RequestParam("shopId") Long shopId,
            @RequestParam("status") String status
    ) {

        try {
            ownerService.checkValidShop(shopId);
            List<OrderResponse> orderResponses = orderService.getOrderByShopIdAndStatus(shopId, status);
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    // api mới
    @GetMapping("/status/to_packaging")
    public ResponseEntity<?> changeOrderStatusToPackagingStatus(
            @RequestParam("shopId") Long shopId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidShop(shopId);
            orderService.changeOrderStatus(shopId, orderId, Order.PACKAGING);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/status/to_handed_over_to_carrier_status")
    public ResponseEntity<?> changeOrderStatusToHandedOverToCarrier(
            @RequestParam("shopId") Long shopId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidShop(shopId);
            orderService.changeOrderStatus(shopId, orderId, Order.HANDED_OVER_TO_CARRIER);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/status/to_shipping")
    public ResponseEntity<?> changeOrderStatusToShipping(
            @RequestParam("shippingProviderId") Long shippingProviderId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            // check valid shipping provider
            ownerService.checkValidShippingProvider(shippingProviderId);
            orderService.changeOrderStatus(shippingProviderId, orderId, Order.SHIPPING);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/status/to_completed")
    public ResponseEntity<?> changeOrderStatusToCompleted(
            @RequestParam("shippingProviderId") Long shippingProviderId,
            @RequestParam("orderId") Long orderId
    ) {

        try {

            Map<String, Integer> totalMoney = new HashMap<>();
            ownerService.checkValidShippingProvider(shippingProviderId);
            orderService.changeOrderStatus(shippingProviderId, orderId, Order.COMPLETED);

            // get shop total money
            Long shopId = orderService.getShopIdByOrderId(orderId);
            totalMoney.put("shop_total_money", shopRepo.getShopTotalMoney(shopId));
            return ResponseEntity.ok(totalMoney);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/status/to_returning")
    public ResponseEntity<?> changeOrderStatusToReturn(
            @RequestParam("shippingProviderId") Long shippingProviderId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidShippingProvider(shippingProviderId);
            orderService.changeOrderStatus(shippingProviderId, orderId, Order.RETURNING);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    @GetMapping("/status/to_completed_returning")
    public ResponseEntity<?> changeOrderStatusToCompletedReturning(
            @RequestParam("shippingProviderId") Long shippingProviderId,
            @RequestParam("orderId") Long orderId
    ) {

        try {
            ownerService.checkValidShippingProvider(shippingProviderId);
            orderService.changeOrderStatus(shippingProviderId, orderId, Order.COMPLETED_RETURNING);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }



    @PostMapping("/add_shipping_provider")
    public ResponseEntity<?> addShippingProviderToOrder(
            @RequestBody OrderShippingProviderDTO orderShippingProviderDTO
    ) {

        try {
            ownerService.checkValidShop(orderShippingProviderDTO.getShopId());
            orderService.addShippingProviderToOrder(orderShippingProviderDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }

    // api for shipping provider


    @GetMapping("/get_shipping_provider_order")
    public ResponseEntity<?> getAllOrderByShippingProviderIdAndOrderStatus(
            @RequestParam("shippingProviderId") Long shippingProviderId,
            @RequestParam("status") String status
    ) {

        try {
            ownerService.checkValidShippingProvider(shippingProviderId);
            List<OrderResponse> orderResponses = orderService
                    .getAllOrderByShippingProviderIdAndOrderStatus(shippingProviderId, status);
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }

    }


}
