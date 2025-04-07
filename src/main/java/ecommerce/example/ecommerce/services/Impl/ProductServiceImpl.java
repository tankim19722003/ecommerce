package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.CloudinaryService;
import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.*;
import ecommerce.example.ecommerce.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubCategoryRepo categoryRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    public CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Autowired
    private ProductDiscountRepo productDiscountRepo;

    @Autowired
    private VoucherRepo voucherRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private SubProductCategoryRepo subProductCategoryRepo;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductAttributeValueService productAttributeValueService;

    @Autowired
    private ProductShippingTypeService productShippingTypeService;

    @Override
    public ProductKeywordPageResponse getProductsByKeyWord(PageRequest pageRequest, String keyword) {
        Page<Product> products = productRepo.getProductsByKeyword(keyword, pageRequest);
        List<ProductKeywordResponse> productKeywordResponses = new ArrayList<>();

        if (products.isEmpty()) throw new RuntimeException("Product does not found");

        for (Product product : products.getContent()) {
            ProductKeywordResponse productKeywordResponse = mapper.map(product, ProductKeywordResponse.class);
            ImageResponse imageResponse = ImageResponse.builder()
                    .publicId(product.getThumbnailPublicId())
                    .avatarUrl(product.getThumbnailUrl())
                    .build();

            productKeywordResponse.setImageResponse(imageResponse);
            productKeywordResponses.add(productKeywordResponse);
        }

        ProductKeywordPageResponse productKeywordPageResponse = new ProductKeywordPageResponse();
        productKeywordPageResponse.addProductKeywordResponses(productKeywordResponses);

        productKeywordPageResponse.setTotalPage(products.getTotalPages());
        return productKeywordPageResponse;
    }

    @Override
    @Transactional
    public ProductCreatingResponse createProduct(Long shopId, ProductCreatingDTO productCreatingDTO) {

        SubCategory category = categoryRepo.findById(productCreatingDTO.getSubcategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

        Shop shop = shopRepo.findById(shopId).orElseThrow(
                () ->  new RuntimeException("Shop does not found")
        );


        // create product response
        ProductCreatingResponse productCreatingResponse = new ProductCreatingResponse();
        productCreatingResponse.setName(productCreatingDTO.getName());
        productCreatingResponse.setDescription(productCreatingDTO.getDescription());
        productCreatingResponse.setSubcategoryId(productCreatingDTO.getSubcategoryId());
        productCreatingResponse.setSubcategoryName(category.getName());

        // save the avatar to the cloud
        Map<String, String> cloudAvatar;
        try {
             cloudAvatar = cloudinaryService.uploadImage(productCreatingDTO.getThumbnail());

             // add thumnail to response
             ImageResponse thumbnail = new ImageResponse();
            thumbnail.setAvatarUrl(cloudAvatar.get("imageUrl"));
            thumbnail.setPublicId(cloudAvatar.get("publicId"));
            productCreatingResponse.setThumbnail(thumbnail);

        } catch (IOException e) {
            throw new RuntimeException("Can't save avatar to the cloud");
        }


        Product product = Product.builder()
                .name(productCreatingDTO.getName())
                .description(productCreatingDTO.getDescription())
                .subCategory(category)
                .thumbnailUrl(cloudAvatar.get("imageUrl"))
                .thumbnailPublicId(cloudAvatar.get("publicId"))
                .shop(shop)
                .build();

        // save product
        productRepo.save(product);

        Product savedProduct = productRepo.save(product);

        productCreatingResponse.setProductId(savedProduct.getId());
        productCreatingResponse.setTotalSold(product.getTotalSold());

        // save the product images
        for (MultipartFile image : productCreatingDTO.getProductImages()) {
            Map<String, String> cloudImage;
            try {
                cloudImage = cloudinaryService.uploadImage(image);
            } catch (IOException e) {
                throw new RuntimeException("Can't save the product image");
            }

            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setUrl(cloudImage.get("imageUrl"));
            productImage.setPublicId(cloudImage.get("publicId"));

            try {
                productImageRepo.save(productImage);
            } catch (Exception e) {
                throw new RuntimeException("Can't save product image to db");
            }

            ImageResponse productImageItem = new ImageResponse();
            productImageItem.setPublicId(cloudImage.get("publicId"));
            productImageItem.setAvatarUrl(cloudImage.get("imageUrl"));

            productCreatingResponse.addProductImage(productImageItem);

        }

        return productCreatingResponse;

    }

    @Override
    public QuantityResponse addQuantityToAttributeProduct(List<QuantityDTO> quantityDTOList, Long productId) {
        return null;
    }

    @Override
    public ProductRatingOrderPageResponse getProductsWithRatingOrder(
            PageRequest pageRequest
    ) {
        Page<Product> productsPage= productRepo.findAll(pageRequest);

        List<Product> products = productsPage.getContent();

        List<ProductRatingOrderResponse> productRatingOrderResponses = new ArrayList<>();


        for (Product product : products) {

            ProductRatingOrderResponse productRatingOrderResponse = new ProductRatingOrderResponse();
            productRatingOrderResponse.setId(product.getId());
            productRatingOrderResponse.setTotalSold(product.getTotalSold());
            productRatingOrderResponse.setName(product.getName());
            productRatingOrderResponse.setRating(product.getRating());

            // voucher response
            List<Voucher> vouchers = voucherRepo.findValidVouchersByProductId(product.getShop().getId(), LocalDateTime.now());
            if (!vouchers.isEmpty()) {
                List<VoucherResponse> voucherResponses = vouchers
                        .stream()
                        .map(voucher -> mapper.map(voucher, VoucherResponse.class))
                        .toList();
                productRatingOrderResponse.addVoucherResponse(voucherResponses);
            }


            // product discount
            LocalDateTime now = LocalDateTime.now();
            ProductDiscount productDiscount = productDiscountRepo.findByDateStartLessThanEqualAndDateEndGreaterThanEqual(LocalDateTime.now(), LocalDateTime.now()).orElse(null);

            if (productDiscount != null) {
                ProductDiscountResponse productDiscountResponse = new ProductDiscountResponse();
                productDiscountResponse.setId(productDiscount.getId());
                productDiscountResponse.setDateEnd(productDiscount.getDateStart());
                productDiscountResponse.setDateStart(productDiscount.getDateStart());
                productDiscountResponse.setDiscountPercent(productDiscount.getDiscountPercent());
                productRatingOrderResponse.setDiscountResponse(productDiscountResponse);
            }

            // image
            List<ProductImage> productImages = productImageRepo.findByProductId(product.getId());
            List<ImageResponse> productImageResponses = new ArrayList<>();
            if (!productImages.isEmpty()) {

                productImageResponses = productImages
                        .stream()
                        .map(productImage -> ImageResponse
                                    .builder()
                                    .avatarUrl(productImage.getUrl())
                                    .publicId(productImage.getPublicId())
                                    .build()
                        ).toList();
            }

            productRatingOrderResponse.setTotalSold(product.getTotalSold());

            int price = 0;

            List<ProductCategory> productCategories = productCategoryRepo.getProductCategoriesByProductId(product.getId());
            if (!productCategories.isEmpty()) {
                ProductCategory productCategory = productCategories.getFirst();
                if (productCategory.getPrice() != 0) {
                    price = product.getProductCategoryGroup().getFirst().getProductCategories().getFirst().getPrice();
                } else {
                    List<SubProductCategory> subProductCategories = subProductCategoryRepo.getSubcategoryByProductId(product.getId());
                    price = subProductCategories.getFirst().getPrice();
                }
            }

            productRatingOrderResponse.setPrice(price);

            productRatingOrderResponse.addImageResponses(productImageResponses);
            productRatingOrderResponses.add(productRatingOrderResponse);
        }

        ProductRatingOrderPageResponse productRatingOrderPageResponse = new ProductRatingOrderPageResponse();
        productRatingOrderPageResponse.addProductRatingOrderResponses(productRatingOrderResponses);
        productRatingOrderPageResponse.setTotalPage(productsPage.getTotalPages());

        return productRatingOrderPageResponse;
    }

    @Override
    public ProductDetailResponse getProductDetails(Long productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("product does not found"));

        // product basic info
        ProductDetailResponse productDetailResponse = new ProductDetailResponse();

        ProductBasicInfoResponse productBasicInfoResponse = new ProductBasicInfoResponse();
        productBasicInfoResponse.setProductId(product.getId());
        productBasicInfoResponse.setName(product.getName());
        productBasicInfoResponse.setDescription(product.getDescription());

        ImageResponse thumbnail = new ImageResponse();
        thumbnail.setAvatarUrl(product.getThumbnailUrl());
        thumbnail.setPublicId(product.getThumbnailPublicId());
        productBasicInfoResponse.setThumbnail(thumbnail);

        SubCategoryResponse subProductCategory = new SubCategoryResponse();
        subProductCategory.setDescription(product.getSubCategory().getDescription());
        subProductCategory.setName(product.getSubCategory().getName());
        subProductCategory.setCreatedAt(product.getSubCategory().getCreatedAt());
        subProductCategory.setUpdatedAt(product.getSubCategory().getUpdatedAt());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(product.getSubCategory().getCategory().getId());
        categoryResponse.setName(product.getSubCategory().getCategory().getName());
        categoryResponse.setDescription(product.getSubCategory().getCategory().getDescription());
        categoryResponse.setCreatedAt(product.getSubCategory().getCategory().getCreatedAt());
        categoryResponse.setUpdatedAt(product.getSubCategory().getCategory().getUpdatedAt());

        subProductCategory.setCategoryResponse(categoryResponse);
        productBasicInfoResponse.setSubCategoryResponse(subProductCategory);

        productDetailResponse.setProductBasicInfo(productBasicInfoResponse);
        // product category
        ProductCategoryResponse productCategoryResponse = productCategoryService.getProductCategories(productId);
        productDetailResponse.setProductCategoryResponses(productCategoryResponse);

        // product images
        List<ImageResponse> productImageResponses = productImageService.getProductImages(productId);
        productDetailResponse.setProductImages(productImageResponses);

        // product attribute value
        List<ProductAttributeValueResponse> productAttributeValueResponses = productAttributeValueService.getAllProductAttributeValue(productId);
        productDetailResponse.setProductAttributeValueResponses(productAttributeValueResponses);

        // shipping
        ProductShippingTypeResponse productShippingTypeResponse = productShippingTypeService.getAllProductShipping(productId);
        if (productShippingTypeResponse != null)
        productDetailResponse.setProductShippingTypeResponse(productShippingTypeResponse.getShippingTypeResponses());

        return productDetailResponse;
    }

}
