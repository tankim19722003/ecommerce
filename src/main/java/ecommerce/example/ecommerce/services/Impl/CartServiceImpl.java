package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.CartDTO;
import ecommerce.example.ecommerce.dtos.CartItemUpdatingDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.CartService;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private SubProductCategoryRepo subProductCategoryRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductDiscountRepo productDiscountRepo;

    @Autowired
    private VoucherRepo voucherRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ProductShippingService productShippingService;

    @Override
    @Transactional
    public void addProductToCart(CartDTO cartDTO) {

        // check is product existing in cart
        int productExistingInCart = cartItemRepo.isProductExistingInCart(
                cartDTO.getUserId(), cartDTO.getProductId(), cartDTO.getProductCategoryId(), cartDTO.getProductSubcategoryId()
        );

        if (productExistingInCart > 0) throw new RuntimeException("Product is existing in cart!");

        CartItem cartItem = new CartItem();

        User user = userRepo.findById(cartDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not found"));

        Product product = productRepo.findById(cartDTO.getProductId())
                .orElseThrow(() ->  new RuntimeException("Product does not found"));

        ProductCategory productCategory = productCategoryRepo
                .findById(cartDTO.getProductCategoryId())
                .orElse(null);

        if (cartDTO.getProductSubcategoryId() != null) {
            SubProductCategory subProductCategory = subProductCategoryRepo
                    .findById(cartDTO.getProductSubcategoryId())
                    .orElseThrow(() ->  new RuntimeException("Subcategory does not found"));
            cartItem.setSubProductCategory(subProductCategory);


            if (subProductCategory.getQuantity() < cartDTO.getQuantity())
                throw new RuntimeException("Quantity of product need less or equal the quantity in the stock!!");

            cartItem.setQuantity(cartDTO.getQuantity());
        } else {

            if (productCategory != null) {
                if (productCategory.getQuantity() < cartDTO.getQuantity())
                    throw new RuntimeException("Quantity of product need less or equal the quantity in the stock!!");

                cartItem.setQuantity(cartDTO.getQuantity());
            }

        }


        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setProductCategory(productCategory);


        cartItemRepo.save(cartItem);

    }

    @Override
    public List<ShopCartItemResponse> getAllCartItems(Long userId) {
       List<CartItem> cartItems = cartItemRepo.findByUserId(userId);

       // get shop id
        Set<Long> shopIds = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            shopIds.add(cartItem.getProduct().getShop().getId());
        }

        List<ShopCartItemResponse> shopCartItemResponses = new ArrayList<>();

       for (Long shopId : shopIds) {
           // get shop info
           Shop shop = shopRepo.findById(shopId).orElseThrow(
                   () ->  new RuntimeException("Shop does not found!!")
           );
           ShopCartItemResponse shopCartItemResponse = new ShopCartItemResponse();

           shopCartItemResponse.setShopBasicInfoResponse(shop.toShopBasicInfo());

           // set shipping type response

           int maxShippingType = -1;
           int indexMaxShippingType = -1;
           int index = 0;

           for (CartItem cartItem : cartItems) {
               if (cartItem.getProduct().getShop().getId() == shopId) {
                    shopCartItemResponse.addCartItem(convertToCartItemResponse(cartItem));

                   // shipping type price
                   float calWeight = productShippingService.getCalWeight(
                           cartItem.getProduct().getHeight(),
                           cartItem.getProduct().getWidth(),
                           cartItem.getProduct().getHigh(),
                           cartItem.getProduct().getWeight()
                   );

                   int shippingFee =(int) calWeight * cartItem.getProduct().getProductShippingTypes().getFirst().getShippingType().getPrice();

                   if (shippingFee > maxShippingType) {
                       maxShippingType = shippingFee;
                       indexMaxShippingType = index;
                   }
               }

               index++;
           }

           // set shipping type
            List<ShippingTypeResponse> shippingTypeResponses = productShippingService.getProductShippingTypes(cartItems.get(indexMaxShippingType).getProduct().getId());
           shopCartItemResponse.setShippingTypeResponses(shippingTypeResponses);

           shopCartItemResponses.add(shopCartItemResponse);

       }

        return shopCartItemResponses;

    }

    @Override
    public CartItemResponse updateQuantityOfCartItem(CartItemUpdatingDTO cartItemUpdatingDTO) {

        CartItem cartItem = cartItemRepo.findById(cartItemUpdatingDTO.getCartItemId())
                .orElseThrow(() -> new RuntimeException("Cart Item does not found"));

        if (cartItem.getUser().getId() != cartItemUpdatingDTO.getUserId())
            throw new RuntimeException("Invalid user");

        // compare new quantity with quantity in the stock
        if (cartItem.getSubProductCategory() != null) {
            if (cartItem.getSubProductCategory().getQuantity() < cartItemUpdatingDTO.getQuantity())
                throw new RuntimeException("Quantity of product need less or equal the quantity in the stock!!");
        } else {
            if (cartItem.getProductCategory().getQuantity() < cartItemUpdatingDTO.getQuantity())
                throw new RuntimeException("Quantity of product need less or equal the quantity in the stock!!");
        }


        cartItem.setQuantity(cartItemUpdatingDTO.getQuantity());

        cartItemRepo.save(cartItem);

        // convert cart item updating to cart item response
        return convertToCartItemResponse(cartItem);

    }

    @Override
    public void deleteProductOutOfCart(Long userId, Long cartItemId) {

        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() ->  new RuntimeException("Cart item does not found"));

        if (cartItem.getUser().getId() != userId)
            throw new RuntimeException("Invalid user");

        cartItemRepo.deleteById(cartItemId);
    }

    private CartItemResponse convertToCartItemResponse(CartItem cartItem) {
        CartItemResponse cartItemResponse = new CartItemResponse();

        cartItemResponse.setProductId(cartItem.getProduct().getId());
        cartItemResponse.setProductName(cartItem.getProduct().getName());

        // set id for cart item response
        cartItemResponse.setCartItemId(cartItem.getId());

        // set price
        int price = 0;
        int stockQuantity = 0;

        // set product category for cart item
        if (cartItem.getProductCategory() != null) {
            cartItemResponse.setProductCategoryId(cartItem.getProductCategory().getId());
            cartItemResponse.setProductCategoryName(cartItem.getProductCategory().getValue());

            ImageResponse productCartImage = ImageResponse.builder()
                    .publicId(cartItem.getProductCategory().getPublicId())
                    .avatarUrl(cartItem.getProductCategory().getImageUrl())
                    .build();

            cartItemResponse.setProductCategoryImage(productCartImage);
        }

        if (cartItem.getSubProductCategory() != null) {
            price = cartItem.getSubProductCategory().getPrice() * cartItem.getQuantity();
            stockQuantity = cartItem.getSubProductCategory().getQuantity();

            // set subcategory
            cartItemResponse.setSubcategoryId(cartItem.getSubProductCategory().getId());
            cartItemResponse.setSubcategoryName(cartItem.getSubProductCategory().getName());

        } else if (cartItem.getProductCategory() != null){
            price = cartItem.getProductCategory().getPrice() * cartItem.getQuantity();
            stockQuantity = cartItem.getProductCategory().getQuantity();
        }
        cartItemResponse.setPrice(price);

        // set discount percent
        Optional<ProductDiscount> productDiscount = productDiscountRepo
                .findByProductId(cartItem.getProduct().getId(), LocalDateTime.now());

        if (productDiscount.isPresent()) {
            cartItemResponse.setDiscountPercent(productDiscount.get().getDiscountPercent());
        }


        // set quantity
        cartItemResponse.setQuantity(cartItem.getQuantity());

        // set stock quantity
        cartItemResponse.setStockQuantity(stockQuantity);

        // set shop voucher
        List<Voucher> vouchers = voucherRepo.findValidVouchersByShopId(cartItem.getProduct().getShop().getId(), LocalDateTime.now());

        List<VoucherResponse> voucherResponses = vouchers.stream()
                .map(voucher ->
                        VoucherResponse
                                .builder()
                                .id(voucher.getId())
                                .code(voucher.getCode())
                                .description(voucher.getDescription())
                                .startDate(voucher.getStartDate())
                                .minimumOrderValue(voucher.getMinimumOrderValue())
                                .discountPercent(voucher.getDiscountPercent())
                                .endDate(voucher.getStartDate())
                                .endDate(voucher.getEndDate())
                                .build()
                )
                .toList();

        cartItemResponse.setVoucherResponses(voucherResponses);
        return cartItemResponse;
    }
}
