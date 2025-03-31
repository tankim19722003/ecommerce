package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ShippingTypesRepo;
import ecommerce.example.ecommerce.models.ShippingType;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.services.ShippingTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingTypeServiceImpl implements ShippingTypeService {

    @Autowired
    private ShippingTypesRepo shippingTypesRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ShippingTypeResponse> getAllShippingType() {
        List<ShippingType> shippingTypes = shippingTypesRepo.findAll();

        return shippingTypes
                .stream()
                .map(shippingType -> mapper.map(shippingType, ShippingTypeResponse.class))
                .toList();
    }
}
