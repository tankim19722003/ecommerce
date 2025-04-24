package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderDetailDTO;
import ecommerce.example.ecommerce.dtos.OrderShippingProviderDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.OrderService;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SubProductCategoryRepo subProductCategoryRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ProductDiscountRepo productDiscountRepo;

    @Autowired
    private VoucherRepo voucherRepo;

    @Autowired
    private ProductShippingService productShippingService;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ShippingProviderRepo shippingProviderRepo;

    @Autowired
    private WalletRepo walletRepo;


    @Override
    @Transactional
    public OrderResponse createOrder(OrderDTO orderDTO) {

        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not found"));

        UserVillage userVillage = userVillageRepo.findById(orderDTO.getUserVillageId())
                .orElseThrow(() -> new RuntimeException("Invalid Address"));

        ProductShippingType productShippingType = productShippingTypeRepo.findById(orderDTO.getProductShippingTypeId())
                .orElseThrow(() -> new RuntimeException("Product shipping type does not found"));


        // add shop
        Shop shop = shopRepo.findById(orderDTO.getShopId())
                .orElseThrow(() ->  new RuntimeException("Shop does not found"));


        // create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.PENDING);
        order.setProductShippingType(productShippingType);
        order.setPhoneNumber(userVillage.getPhoneNumber());
        order.setReceiverName(userVillage.getReceiverName());
        order.setShop(shop);
        order.setNotes(orderDTO.getNote());
        order.setExpectedReceiveDate(LocalDateTime.now().plusDays(productShippingType.getShippingType().getEstimatedTime()));

        orderRepo.save(order);

        //create order response
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderStatus(order.getStatus());
        orderResponse.setNote(order.getNotes());

        // create address for order
        UserVillageOrder userVillageOrder = new UserVillageOrder();
        userVillageOrder.setOrder(order);
        userVillageOrder.setVillage(userVillage.getVillage());
        userVillageOrder.setSpecificAddress(userVillage.getSpecificVillage());

        userVillageOrderRepo.save(userVillageOrder);

        order.setUserVillageOrder(userVillageOrder);
        // save order detail
        int totalPrice = 0;
        int shippingPrice = 0;


        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetailDTOs()) {
            OrderDetail orderDetail = new OrderDetail();

            // get product
            Product product = productRepo.findById(orderDetailDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product does not found"));

            if (product.getShop().getId() != orderDTO.getShopId()) {
                throw new RuntimeException("Product with id "+ product.getId()+ " does not belong to shop with shop id " + orderDTO.getShopId());
            }

            // calculate shipping price
            float shippingPriceItem = productShippingService.getCalWeight(product.getHeight(), product.getWidth(), product.getHigh(), product.getWeight())
                    * productShippingType.getShippingType().getPrice();

            if (shippingPriceItem > shippingPrice) shippingPrice = (int)shippingPriceItem;

            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());

            // get discount
            int discountPercent = 0;
            Optional<ProductDiscount> productDiscount = productDiscountRepo
                    .findByProductId(orderDetail.getProduct().getId(), LocalDateTime.now());


            if (productDiscount.isPresent()) {
                discountPercent = productDiscount.get().getDiscountPercent();
                orderDetail.setDiscountPercent(discountPercent);
            }


            orderDetailResponse = orderDetail.toOrderDetailResponse();
            // set price

            ProductCategory productCategory = productCategoryRepo.findById(orderDetailDTO.getProductCategoryId())
                    .orElseThrow(()->new RuntimeException("Product category does not found"));

            // set product category for order detail
            orderDetail.setProductCategory(productCategory);

            orderDetailResponse.setProductCategoryId(productCategory.getId());
            orderDetailResponse.setProductCategoryName(productCategory.getValue());
            orderDetailResponse.setProductCategoryImageUrl(productCategory.getImageUrl());

            if (orderDetailDTO.getProductSubcategoryId() != null) {

                SubProductCategory subProductCategory = subProductCategoryRepo.findById(orderDetailDTO.getProductSubcategoryId())
                        .orElseThrow(() ->  new RuntimeException("product subcategory does not found"));

                // compare quantity with stock quantity
                if(orderDetailDTO.getQuantity() > subProductCategory.getQuantity())
                    throw new RuntimeException("Quantity must less or equals than stock quantity!!");

                orderDetail.setPrice(subProductCategory.getPrice());
                int detailPrice = subProductCategory.getPrice() * orderDetail.getQuantity();
                orderDetailResponse.setProductSubCategoryId(subProductCategory.getId());
                orderDetailResponse.setProductSubCategoryName(subProductCategory.getName());

                // set total price for product detail
                if (discountPercent > 0 ) {
                    detailPrice = detailPrice - (detailPrice * discountPercent) / 100;
                }
                totalPrice += detailPrice;
                subProductCategory.setQuantity(subProductCategory.getQuantity() - orderDetailDTO.getQuantity());
                orderDetailResponse.setTotalPrice(detailPrice);

                subProductCategoryRepo.save(subProductCategory);

                // set subproduct category for the order detail
                orderDetail.setSubProductCategory(subProductCategory);

            } else {
                if(orderDetailDTO.getQuantity() > productCategory.getQuantity())
                    throw new RuntimeException("Quantity must less or equals than stock quantity!!");

                orderDetail.setPrice(productCategory.getPrice());

                int detailPrice = productCategory.getPrice() * orderDetail.getQuantity();

                productCategory.setQuantity(productCategory.getQuantity() - orderDetailDTO.getQuantity());
                productCategoryRepo.save(productCategory);

                if (discountPercent > 0 ) {
                    detailPrice = detailPrice - (detailPrice * discountPercent) / 100;
                }

                totalPrice += detailPrice;

                // set detail price for the order
                orderDetailResponse.setTotalPrice(detailPrice);

            }

            orderDetailResponse.setPrice(orderDetail.getPrice());
            // save order detail
            orderDetailRepo.save(orderDetail);

            // create order detail response
            orderDetailResponse.setId(orderDetail.getId());
            orderResponse.addOrderDetailResponse(orderDetailResponse);

        }


        totalPrice += shippingPrice;
//        set voucher response
        if (orderDTO.getVoucherId() != null) {
            Optional<Voucher> voucher = voucherRepo.findById(orderDTO.getVoucherId());
            if (voucher.isPresent()) {
                if (voucher.get().getMinimumOrderValue() < totalPrice) {
                    totalPrice = totalPrice - (totalPrice * voucher.get().getDiscountPercent()) / 100;
                    orderResponse.setTotalMoney(totalPrice);

                    // set voucher discount
                    order.setDiscountPercent(voucher.get().getDiscountPercent());

                    order.setVoucher(voucher.get());

                    // add voucher response
                    VoucherResponse voucherResponse = new VoucherResponse();
                    voucherResponse.setId(voucher.get().getId());
                    voucherResponse.setDescription(voucher.get().getDescription());
                    voucherResponse.setCode(voucher.get().getCode());
                    voucherResponse.setDiscountPercent(voucher.get().getDiscountPercent());
                    voucherResponse.setMinimumOrderValue(voucher.get().getMinimumOrderValue());
                    voucherResponse.setEndDate(voucher.get().getEndDate());
                    voucherResponse.setStartDate(voucher.get().getStartDate());

                    orderResponse.setVoucherResponse(voucherResponse);
                } else {
                    throw new RuntimeException("Your total price need to greater or equals than minimum value of the voucher");
                }
            }
        }


        orderResponse.setTotalMoney(totalPrice);

        // set total price for the order
        order.setTotalPrice(totalPrice);
        order.setShippingFee(shippingPrice);


        orderRepo.save(order);
        // shipping type response
        ShippingTypeResponse shippingTypeResponse = new ShippingTypeResponse();
        shippingTypeResponse.setId(productShippingType.getId());
        shippingTypeResponse.setName(productShippingType.getShippingType().getName());
        shippingTypeResponse.setDescription(productShippingType.getShippingType().getDescription());
        shippingTypeResponse.setEstimatedTime(productShippingType.getShippingType().getEstimatedTime());
        shippingTypeResponse.setPrice(shippingPrice);
        orderResponse.setShippingTypeResponse(shippingTypeResponse);

        // user village response
        UserVillageResponse userVillageResponse = userVillage.toUserAddressResponse();
        orderResponse.setUserVillageResponse(userVillageResponse);
        return orderResponse;
    }

    @Override
    public List<CompletingOrderResponse> getOrderByStatus(Long userId, String status) {

        // get order by user
        List<Order> orders = orderRepo.findAllByUserIdAndStatus(userId, status);


        if (orders.isEmpty()) throw new RuntimeException("You don't have any order");

        List<CompletingOrderResponse> completingOrderResponses = new ArrayList<>();
        for (Order order : orders) {
            CompletingOrderResponse completingOrderResponse = new CompletingOrderResponse();

            // order info
            completingOrderResponse.setOrderPrice(order.getTotalPrice());
            completingOrderResponse.setOrderId(order.getId());

            // shop info
            Long shopId = order.getOrderDetails().getFirst().getProduct().getShop().getId();
            String shopName = order.getOrderDetails().getFirst().getProduct().getShop().getShopName();
            completingOrderResponse.setShopId(shopId);
            completingOrderResponse.setShopName(shopName);
            completingOrderResponse.setCreatedAt(order.getCreatedAt());
            completingOrderResponse.setUpdatedAt(order.getUpdatedAt());

            // list order responses
            List<OrderDetailResponse> orderDetailResponses = toOrderDetailResponse(order.getOrderDetails());

            completingOrderResponse.setOrderDetailResponses(orderDetailResponses);

            // add completing order response to response list
            completingOrderResponses.add(completingOrderResponse);


        }

        return completingOrderResponses;

    }

    @Override
    @Transactional
    public void cancelOrder(Long userId, Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order does not found"));

        if (order.getUser().getId() != userId)
            throw new RuntimeException("You are not allowed to modify this data");

        if (!order.getStatus().equals(Order.PENDING))
            throw new RuntimeException("Order can't be cancel");

        order.setStatus(Order.CANCEL);

        orderRepo.save(order);
    }

    @Override
    public List<OrderResponse> getOrderByShopIdAndStatus(Long shopId, String status) {
        List<Order> shopOrder = orderRepo.findAllByShopIdAndStatus(shopId, status);

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : shopOrder) {

            orderResponses.add(convertOrderToOrderResponse(order));

        }

        return orderResponses;


    }

    @Override
    public void changeOrderStatusToPackagingStatus(Long shopId, Long orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order does not found"));

        if (order.getShop().getId() != shopId)
            throw new RuntimeException("You are not allowed to access to this data");

        if (!order.getStatus().equals(Order.PENDING))
            throw new RuntimeException("The order is not pending status");

        order.setStatus(Order.PACKAGING);
        orderRepo.save(order);

    }

    @Override
    @Transactional
    public void changeOrderStatus(Long ownerId, Long orderId, String status) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order does not found"));

        // check is the shop or shipping provider own this order
       if (    status.equals(Order.PACKAGING) ||
               status.equals(Order.HANDED_OVER_TO_CARRIER)
       ) {
           if (order.getShop().getId() != ownerId)
               throw new RuntimeException("You are not allowed to access to this data");
       }

        if (status.equals(Order.SHIPPING) ||
                status.equals(Order.RETURNING) ||
                status.equals(Order.COMPLETED_RETURNING)
        ) {
            if (order.getShippingProvider().getId() != ownerId)
                throw new RuntimeException("You are not allowed to access to this data");
        }




        if(order.getStatus().equals(Order.PENDING) && !status.equals(Order.PACKAGING)) {
            throw new RuntimeException("Can't change the order status");
        }

        if(order.getStatus().equals(Order.PACKAGING) && !status.equals(Order.HANDED_OVER_TO_CARRIER)) {
            throw new RuntimeException("Can't change the order status");
        }

        if(order.getStatus().equals(Order.HANDED_OVER_TO_CARRIER) && !status.equals(Order.SHIPPING)) {
            throw new RuntimeException("Can't change the order status");
        }

        if(order.getStatus().equals(Order.SHIPPING)) {
           if (!(status.equals(Order.RETURNING) || status.equals(Order.COMPLETED)))
            throw new RuntimeException("Can't change the order status");
        }


        if (order.getStatus().equals(Order.RETURNING) && !status.equals(Order.COMPLETED_RETURNING)) {
            throw new RuntimeException("Can't change the order status");
        }


        if (order.getStatus().equals(Order.COMPLETED_RETURNING) || order.getStatus().equals(Order.COMPLETED)) {
            throw new RuntimeException("Can't change the order status");
        }



        // logic to pay the money for shop
        // get 2 percent per order
        if (status.equals(Order.COMPLETED)) {
            Shop shop = shopRepo.findById(order.getShop().getId()).orElseThrow(
                    () -> new RuntimeException("Can't find the shop")
            );

            int totalMoney = (int)(shop.getTotalMoney() + order.getTotalPrice() * 0.98);
            shop.setTotalMoney(totalMoney);

            // increase total money for admin
            User user = userRepo.findByAccount("admin").orElseThrow(
                    () -> new RuntimeException("Admin does not found")
            );

            // set total money for the wallet
            Wallet wallet = walletRepo.findByUserId(user.getId())
                    .orElseThrow(() -> new RuntimeException("Wallet does not found"));

            int totalWalletMoney = (int)(wallet.getTotalMoney() + order.getTotalPrice() * 0.2);
            wallet.setTotalMoney(totalWalletMoney);

            walletRepo.save(wallet);

            // update total sold
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = productRepo.findById(orderDetail.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product does not found"));

                int productTotalSold = product.getTotalSold() + orderDetail.getQuantity();
                 product.setTotalSold(productTotalSold);

                 productRepo.save(product);

            }

        }

        // handle for the completed_returning
        if (status.equals(Order.COMPLETED_RETURNING)) {

            for (OrderDetail orderDetail : order.getOrderDetails()) {

                Product product = productRepo.findById(orderDetail.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product does not found"));

                // increase the quantity of the product after returning
                if (orderDetail.getSubProductCategory() != null) {

                    SubProductCategory subProductCategory = subProductCategoryRepo.findById(orderDetail.getSubProductCategory().getId())
                            .orElseThrow(() -> new RuntimeException("Sub product category does not found"));

                    int quantity = subProductCategory.getQuantity() + orderDetail.getQuantity();

                    subProductCategory.setQuantity(quantity);

                    subProductCategoryRepo.save(subProductCategory);

                } else {

                    ProductCategory productCategory = productCategoryRepo.findById(orderDetail.getProductCategory().getId())
                            .orElseThrow(() -> new RuntimeException("Product category does not found"));

                    int quantity = productCategory.getQuantity() + orderDetail.getQuantity();

                    productCategory.setQuantity(quantity);

                    productCategoryRepo.save(productCategory);

                }
            }


        }

        order.setStatus(status);

        orderRepo.save(order);

    }

    @Override
    @Transactional
    public void addShippingProviderToOrder(OrderShippingProviderDTO orderShippingProvider) {

        Order order = orderRepo.findById(orderShippingProvider.getOrderId())
                .orElseThrow(() ->  new RuntimeException("Order does not found"));

        if (order.getShop().getId() != orderShippingProvider.getShopId())
            throw new RuntimeException("You are not allowed to modify this data!!");

        ShippingProvider shippingProvider = shippingProviderRepo.findById(orderShippingProvider.getShippingProviderId())
                .orElseThrow(() -> new RuntimeException("Shipping provider does not found"));

        order.setShippingProvider(shippingProvider);

    }

    @Override
    public List<OrderResponse> getAllOrderByShippingProviderIdAndOrderStatus(Long shippingProviderId, String status) {

        List<Order> orders = orderRepo.findAllByShippingProviderIdAndStatus(shippingProviderId, status);

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(convertOrderToOrderResponse(order));
        }

        return orderResponses;

    }

    @Override
    public Long getShopIdByOrderId(Long orderId) {
        return orderRepo.getShopIdByOrderId(orderId);
    }

    @Override
    public int getShopTotalMoney(Long shopId) {
        return shopRepo.getShopTotalMoney(shopId);
    }

    private OrderResponse convertOrderToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderStatus(order.getStatus());
        orderResponse.setNote(order.getNotes());
        orderResponse.setTotalMoney(order.getTotalPrice());
        orderResponse.setOrderStatus(order.getStatus());


        // save order detail
        for (OrderDetail orderDetail : order.getOrderDetails()) {

            OrderDetailResponse orderDetailResponse = orderDetail.toOrderDetailResponse();
            orderResponse.addOrderDetailResponse(orderDetailResponse);

        }


//        set voucher response
        if (order.getVoucher() != null)
            orderResponse.setVoucherResponse(order.getVoucher().toVoucherResponse());


        // shipping type response
        ShippingTypeResponse shippingTypeResponse = new ShippingTypeResponse();
        shippingTypeResponse.setId(order.getProductShippingType().getId());
        shippingTypeResponse.setName(order.getProductShippingType().getShippingType().getName());
        shippingTypeResponse.setDescription(order.getProductShippingType().getShippingType().getDescription());
        shippingTypeResponse.setEstimatedTime(order.getProductShippingType().getShippingType().getEstimatedTime());
        shippingTypeResponse.setPrice(order.getShippingFee());
        orderResponse.setShippingTypeResponse(shippingTypeResponse);

        // user village response
        UserVillageOrderResponse userVillageOrderResponse = order.getUserVillageOrder().toUserVillageOrderResponse();
        orderResponse.setUserVillageResponse(userVillageOrderResponse);
        return orderResponse;

    }


    private List<OrderDetailResponse> toOrderDetailResponse(List<OrderDetail> orderDetails) {

        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            orderDetailResponse.setId(orderDetail.getId());
            orderDetailResponse.setProductName(orderDetail.getProduct().getName());
            orderDetailResponse.setQuantity(orderDetail.getQuantity());
            orderDetailResponse.setPrice(orderDetail.getPrice());
            orderDetailResponse.setDiscountPercent(orderDetail.getDiscountPercent());

            if (orderDetail.getProductCategory() != null) {
                orderDetailResponse.setProductCategoryId(orderDetail.getProductCategory().getId());
                orderDetailResponse.setProductCategoryName(orderDetail.getProductCategory().getValue());
                orderDetailResponse.setProductCategoryImageUrl(orderDetail.getProductCategory().getImageUrl());
            }
            // check sub category
            if (orderDetail.getSubProductCategory() != null) {
                orderDetailResponse.setProductSubCategoryId(orderDetail.getSubProductCategory().getId());
                orderDetailResponse.setProductSubCategoryName(orderDetail.getSubProductCategory().getName());
            }


            int totalPrice = orderDetail.getPrice() * orderDetail.getQuantity();
            if (orderDetail.getDiscountPercent() > 0)
                totalPrice = totalPrice - totalPrice * orderDetail.getDiscountPercent() / 100;

            orderDetailResponse.setTotalPrice(totalPrice);
            orderDetailResponses.add(orderDetailResponse);

        }

        return orderDetailResponses;

    }


}
