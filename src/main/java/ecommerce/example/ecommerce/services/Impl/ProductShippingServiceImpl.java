package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ShippingTypesRepo;
import ecommerce.example.ecommerce.dtos.ProductShippingInfoDTO;
import ecommerce.example.ecommerce.dtos.ShippingFeeDTO;
import ecommerce.example.ecommerce.models.ShippingType;
import ecommerce.example.ecommerce.responses.ShippingFeeResponse;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.services.ProductShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductShippingServiceImpl implements ProductShippingService {

    @Autowired
    private ShippingTypesRepo shippingTypesRepo;

    @Override
    public List<ShippingFeeResponse> calculateShippingFee(ShippingFeeDTO shippingFeeDTO) {

        List<ShippingType> shippingTypes = shippingTypesRepo.findAll();

        float standardWeight = (float) (shippingFeeDTO.getHigh() * shippingFeeDTO.getHeight() * shippingFeeDTO.getHigh()) / 5000;

        float price = 0;
        float calWeight = 0.0f;

        if (standardWeight < shippingFeeDTO.getWeight()) calWeight = shippingFeeDTO.getWeight();
        else calWeight = standardWeight;

        List<ShippingFeeResponse> shippingFeeResponses = new ArrayList<>();
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

        return shippingFeeResponses;

    }

    @Override
    public void createProductShippingInfo(ProductShippingInfoDTO productShippingInfoDTO) {

    }
}
