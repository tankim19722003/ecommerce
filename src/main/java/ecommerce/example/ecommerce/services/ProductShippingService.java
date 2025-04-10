package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.ProductShippingInfoDTO;
import ecommerce.example.ecommerce.dtos.ShippingFeeDTO;
import ecommerce.example.ecommerce.responses.ShippingFeeResponse;

import java.util.List;

public interface ProductShippingService {

    List<ShippingFeeResponse> calculateShippingFee(ShippingFeeDTO shippingFeeDTO);

    void createProductShippingInfo(
            ProductShippingInfoDTO productShippingInfoDTO
    );

}
