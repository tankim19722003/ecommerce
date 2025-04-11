package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.OrderDTO;
import ecommerce.example.ecommerce.dtos.OrderDetailDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.OrderDetailResponse;
import ecommerce.example.ecommerce.responses.OrderResponse;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.responses.UserVillageResponse;
import ecommerce.example.ecommerce.services.OrderService;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

//        Category category = categoryRepo.findById(orderDTO.)


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
        userVillageOrder.setSpecificAddress(userVillageOrder.getSpecificAddress());

        userVillageOrderRepo.save(userVillageOrder);

        // save order detail
        float totalPrice = 0;
        float shippingPrice = 0;


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

            if (shippingPriceItem > shippingPrice) shippingPrice = shippingPriceItem;

            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());

            // get discount
            Optional<ProductDiscount> productDiscount = productDiscountRepo
                    .findByProductId(orderDetail.getProduct().getId(), LocalDateTime.now());


            if (productDiscount.isPresent()) {
                orderDetail.setDiscountPercent(productDiscount.get().getDiscountPercent());
            }


            orderDetailResponse = orderDetail.toOrderDetailResponse();
            // set price

            ProductCategory productCategory = productCategoryRepo.findById(orderDetailDTO.getProductCategoryId())
                    .orElseThrow(()->new RuntimeException("Product category does not found"));


            orderDetailResponse.setProductCategoryId(productCategory.getId());
            orderDetailResponse.setProductCategoryName(productCategory.getValue());
            orderDetailResponse.setProductCategoryImageUrl(productCategory.getImageUrl());

            if (orderDetailDTO.getProductSubcategoryId() != null) {

                SubProductCategory subProductCategory = subProductCategoryRepo.findById(orderDetailDTO.getProductSubcategoryId())
                        .orElseThrow(() ->  new RuntimeException("product subcategory does not found"));

                orderDetail.setPrice(subProductCategory.getPrice());
                totalPrice += subProductCategory.getPrice() * orderDetail.getQuantity();
                orderDetailResponse.setProductSubCategoryId(subProductCategory.getId());
                orderDetailResponse.setProductSubCategoryName(subProductCategory.getName());

                subProductCategory.setQuantity(subProductCategory.getQuantity() - 1);
                subProductCategoryRepo.save(subProductCategory);

            } else {
                orderDetail.setPrice(productCategory.getPrice());
                totalPrice += productCategory.getPrice() * orderDetail.getQuantity();
                productCategory.setQuantity(productCategory.getQuantity() - 1);
                productCategoryRepo.save(productCategory);

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
                totalPrice = totalPrice - totalPrice * (voucher.get().getDiscountPercent() / 100);
                orderResponse.setTotalMoney(totalPrice);
            }
        }

        totalPrice += shippingPrice;
        orderResponse.setTotalMoney(totalPrice);



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
}
