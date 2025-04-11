package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.CartDTO;
import ecommerce.example.ecommerce.dtos.CartItemUpdatingDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.CartItemResponse;
import ecommerce.example.ecommerce.responses.ImageResponse;
import ecommerce.example.ecommerce.responses.ProductDiscountResponse;
import ecommerce.example.ecommerce.responses.VoucherResponse;
import ecommerce.example.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
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
                    .orElse(null);
            cartItem.setSubProductCategory(subProductCategory);

        }


        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setProductCategory(productCategory);
        cartItem.setQuantity(cartDTO.getQuantity());

        cartItemRepo.save(cartItem);

    }

    @Override
    public List<CartItemResponse> getAllCartItems(Long userId) {
       List<CartItem> cartItems = cartItemRepo.findByUserId(userId);

       if (cartItems.size() > 0) {
           return cartItems.stream()
                   .map(cartItem -> {
                       CartItemResponse cartItemResponse = new CartItemResponse();

                       cartItemResponse.setProductId(cartItem.getProduct().getId());
                       cartItemResponse.setProductName(cartItem.getProduct().getName());

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

                       } else {
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

                   }).toList();
       }

       return null;
    }

    @Override
    public CartItemResponse updateQuantityOfCartItem(CartItemUpdatingDTO cartItemUpdatingDTO) {
        return null;
    }

    @Override
    public void deleteProductOutOfCart(Long userId, Long productId) {

    }
}
