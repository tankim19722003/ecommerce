package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.Repo.ProductShippingTypeRepo;
import ecommerce.example.ecommerce.Repo.ShippingTypeRepo;
import ecommerce.example.ecommerce.dtos.ProductShippingInfoDTO;
import ecommerce.example.ecommerce.dtos.ShippingFeeDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductShippingType;
import ecommerce.example.ecommerce.models.ShippingType;
import ecommerce.example.ecommerce.responses.ShippingFeeResponse;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductShippingServiceImpl implements ProductShippingService {

    @Autowired
    private ShippingTypeRepo shippingTypeRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductShippingTypeRepo productShippingTypeRepo;


    @Override
    public List<ShippingFeeResponse> calculateShippingFee(ShippingFeeDTO shippingFeeDTO) {

        List<ShippingType> shippingTypes = shippingTypeRepo.findAll();

        float standardWeight = (float) (shippingFeeDTO.getHigh() * shippingFeeDTO.getHeight() * shippingFeeDTO.getWidth()) / 5000;

        float calWeight = 0.0f;

        if (standardWeight < shippingFeeDTO.getWeight()) calWeight = shippingFeeDTO.getWeight();
        else calWeight = standardWeight;

        List<ShippingFeeResponse> shippingFeeResponses = new ArrayList<>();

        if (calWeight > 1) {
            for (ShippingType shippingType : shippingTypes) {

                ShippingFeeResponse shippingFeeResponse = new ShippingFeeResponse();
                shippingFeeResponse.setPrice(calWeight * shippingType.getPrice());

                // set shipping type
                ShippingTypeResponse shippingTypeResponse = ShippingTypeResponse
                        .builder()
                        .id(shippingType.getId())
                        .price(shippingType.getPrice())
                        .description(shippingType.getDescription())
                        .estimatedTime(shippingType.getEstimatedTime())
                        .name(shippingType.getName())
                        .build();

                shippingFeeResponse.setShippingTypeResponse(shippingTypeResponse);
                shippingFeeResponses.add(shippingFeeResponse);
            }
        } else {

            for (ShippingType shippingType : shippingTypes) {

                ShippingFeeResponse shippingFeeResponse = new ShippingFeeResponse();
                shippingFeeResponse.setPrice((float)shippingType.getPrice());

                // set shipping type
                ShippingTypeResponse shippingTypeResponse = ShippingTypeResponse
                        .builder()
                        .id(shippingType.getId())
                        .price(shippingType.getPrice())
                        .description(shippingType.getDescription())
                        .estimatedTime(shippingType.getEstimatedTime())
                        .name(shippingType.getName())
                        .build();

                shippingFeeResponse.setShippingTypeResponse(shippingTypeResponse);
                shippingFeeResponses.add(shippingFeeResponse);
            }

        }


        return shippingFeeResponses;

    }

    @Override
    @Transactional
    public void createProductShippingInfo(ProductShippingInfoDTO productShippingInfoDTO) {

        Product product  = productRepo.findById(productShippingInfoDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product does not found"));


        // check product owned by shop
        if (product.getShop().getId() != productShippingInfoDTO.getShopId())
            throw new RuntimeException("Invalid shop");


        // save packed product shipping info
        product.setHeight(productShippingInfoDTO.getHeight());
        product.setWeight(productShippingInfoDTO.getWeight());
        product.setHigh(productShippingInfoDTO.getHigh());
        product.setWidth(productShippingInfoDTO.getWidth());

        productRepo.save(product);

        // product shipping type
        for (Long shippingTypeId : productShippingInfoDTO.getShippingTypeIds()) {
            ShippingType shippingType = shippingTypeRepo.findById(shippingTypeId).orElseThrow(
                    () -> new RuntimeException("Shipping type does not found")
            );
            ProductShippingType productShippingType = new ProductShippingType();
            productShippingType.setProduct(product);
            productShippingType.setShippingType(shippingType);
            productShippingTypeRepo.save(productShippingType);
        }

    }

    @Override
    public List<ShippingTypeResponse> getProductShippingTypes(Long productId) {

        List<ProductShippingType> productShippingTypes = productShippingTypeRepo.findAllByProductId(productId);

        if (productShippingTypes != null) {

            return productShippingTypes.stream()
                    .map(productShippingType -> {
                        // calculate shipping fee
                        float calWeight = 0;
                        float price = 0;
                        float standardWeight = (float) (productShippingType.getProduct().getHigh() * productShippingType.getProduct().getHeight() * productShippingType.getProduct().getWidth()) / 5000;

                        if (standardWeight < productShippingType.getProduct().getWeight())
                            calWeight = productShippingType.getProduct().getWeight();
                        else
                            calWeight = standardWeight;

                        if (calWeight < 1)
                             price = productShippingType.getShippingType().getPrice();
                        else
                            price = calWeight * productShippingType.getShippingType().getPrice();



                       return ShippingTypeResponse.builder()
                               .id(productShippingType.getShippingType().getId())
                               .name(productShippingType.getShippingType().getName())
                               .description(productShippingType.getShippingType().getDescription())
                               .price(price)
                               .estimatedTime(productShippingType.getShippingType().getEstimatedTime())
                               .build();

                    }).toList();

        }

        return null;
    }

    @Override
    public float getCalWeight(
            int height, int width, int high, float weight
    ) {
        float standardWeight = (float) (height * width * high) / 5000;

        float calWeight = 0.0f;

        if (standardWeight < weight) calWeight = weight;
        else calWeight = standardWeight;

        return calWeight;
    }
}
