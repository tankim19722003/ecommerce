package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.Repo.ProductShippingTypesRepo;
import ecommerce.example.ecommerce.Repo.ShippingTypesRepo;
import ecommerce.example.ecommerce.dtos.ProductShippingTypeDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductShippingType;
import ecommerce.example.ecommerce.models.ShippingType;
import ecommerce.example.ecommerce.responses.ProductShippingTypeResponse;
import ecommerce.example.ecommerce.responses.ShippingTypeResponse;
import ecommerce.example.ecommerce.services.ProductShippingTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductShippingTypeServiceImpl implements ProductShippingTypeService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ShippingTypesRepo shippingTypesRepo;

    @Autowired
    private ProductShippingTypesRepo productShippingTypesRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public ProductShippingTypeResponse createProductShippingType(
            ProductShippingTypeDTO productShippingTypeDTO
    ) {

        Product product = productRepo.findById(productShippingTypeDTO.getProductId())
                .orElseThrow(() ->  new RuntimeException("Product does not found"));

        if (product.getShop().getId() != productShippingTypeDTO.getShopId())
            throw new RuntimeException("Invalid Shop");


        ProductShippingTypeResponse productShippingTypeResponse = new ProductShippingTypeResponse();
        productShippingTypeResponse.setProductId(productShippingTypeDTO.getProductId());

        for (Long shippingTypeId : productShippingTypeDTO.getShippingTypeIds()) {

            ShippingType shippingType = shippingTypesRepo.findById(shippingTypeId)
                    .orElseThrow(() ->  new RuntimeException("Shipping type does not found"));

            ProductShippingType productShippingTypes = new ProductShippingType();
            productShippingTypes.setShippingType(shippingType);
            productShippingTypes.setProduct(product);

            try {
                productShippingTypesRepo.save(productShippingTypes);
            } catch (DataIntegrityViolationException e) {
                throw new RuntimeException("Product shipping type existing!!");
            }

            // add shipping type to response
            productShippingTypeResponse.addShippingType(mapper.map(shippingType, ShippingTypeResponse.class));
        }

        return productShippingTypeResponse;
    }

    @Override
    public ProductShippingTypeResponse getAllProductShipping(Long productId) {

        List<ProductShippingType> productShippingTypeList = productShippingTypesRepo.findByProductShippingTypeByProductId(productId);

        if (productShippingTypeList.isEmpty())
            return null;

        ProductShippingTypeResponse productShippingTypeResponse = new ProductShippingTypeResponse();
        productShippingTypeResponse.setProductId(productId);

        for (ProductShippingType productShippingTypes : productShippingTypeList) {

            ShippingTypeResponse shippingTypeResponse = new ShippingTypeResponse();
            mapper.map(productShippingTypes.getShippingType(), shippingTypeResponse);

            productShippingTypeResponse.addShippingType(shippingTypeResponse);
        }

        return productShippingTypeResponse;

    }
}
