package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderDetailDTO;
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


    @Override
    @Transactional
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
        order.setNotes(orderDTO.getNote());
        order.setExpectedReceiveDate(LocalDateTime.now().plusDays(productShippingType.getShippingType().getEstimatedTime()));
        order.setOrderDate(LocalDateTime.now());

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
                totalPrice += detailPrice;
                orderDetailResponse.setProductSubCategoryId(subProductCategory.getId());
                orderDetailResponse.setProductSubCategoryName(subProductCategory.getName());

                // set total price for product detail
                if (discountPercent > 0 ) {
                    detailPrice = detailPrice - (detailPrice * discountPercent) / 100;
                }

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

                totalPrice += detailPrice;
                productCategory.setQuantity(productCategory.getQuantity() - orderDetailDTO.getQuantity());
                productCategoryRepo.save(productCategory);

                if (discountPercent > 0 ) {
                    detailPrice = detailPrice - (detailPrice * discountPercent) / 100;
                }

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


//        set voucher response
        if (orderDTO.getVoucherId() != null) {
            Optional<Voucher> voucher = voucherRepo.findById(orderDTO.getVoucherId());
            if (voucher.isPresent()) {
                if (voucher.get().getMinimumOrderValue() < totalPrice) {
                    totalPrice = totalPrice - totalPrice * (voucher.get().getDiscountPercent() / 100);
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

        totalPrice += shippingPrice;
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
    public List<CompletingOrderResponse> getCompletingOrder(Long userId, String status) {

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


            // list order responses
            List<OrderDetailResponse> orderDetailResponses = toOrderResponse(order.getOrderDetails());

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


    private List<OrderDetailResponse> toOrderResponse(List<OrderDetail> orderDetails) {

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
