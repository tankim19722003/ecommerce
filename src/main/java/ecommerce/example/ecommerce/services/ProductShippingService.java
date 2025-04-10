package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductShippingInfoDTO;
import ecommerce.example.ecommerce.dtos.ShippingFeeDTO;
import ecommerce.example.ecommerce.responses.ShippingFeeResponse;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;

import java.util.List;

public interface ProductShippingService {

    List<ShippingFeeResponse> calculateShippingFee(ShippingFeeDTO shippingFeeDTO);

    void createProductShippingInfo(
            ProductShippingInfoDTO productShippingInfoDTO
    );

    List<ShippingTypeResponse> getProductShippingTypes(Long productId);

    float getCalWeight(
            int height, int width, int high, float weight
    );
}
