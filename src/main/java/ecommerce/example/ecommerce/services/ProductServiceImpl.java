package ecommerce.example.ecommerce.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.ProductAttributeValueDTO;
import ecommerce.example.ecommerce.dtos.ProductCreatingDTO;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.AttributeValueResponse;
import ecommerce.example.ecommerce.responses.ProductCreatingResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    @Autowired
    private CategoryAttributeRepo categoryAttributeRepo;

    @Autowired
    private ProductAttributeValueRepo productAttributeValueRepo;

    @Override
    @Transactional
    public ProductCreatingResponse createProduct(ProductCreatingDTO productCreatingDTO) {

        Category category = categoryRepo.findById(productCreatingDTO.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

        Shop shop = shopRepo.findById(productCreatingDTO.getShopId()).orElseThrow(
                () ->  new RuntimeException("Shop does not found")
        );

        Product product = Product.builder()
                .name(productCreatingDTO.getName())
                .description(productCreatingDTO.getDescription())
                .category(category)
                .shop(shop)
                .build();

        Product savedProduct = productRepo.save(product);
        List<ProductAttributeValue> productAttributeValues = new ArrayList<>();

        // handle saving attribute
        for (ProductAttributeValueDTO attributeValue : productCreatingDTO.getAttributes()) {
            CategoryAttribute attribute = categoryAttributeRepo.findById(attributeValue.getId())
                    .orElseThrow(() -> new RuntimeException("Attribute does not found"));

            ProductAttributeValue productAttributeValue = ProductAttributeValue.builder()
                    .product(savedProduct)
                    .categoryAttribute(attribute)
                    .value(attributeValue.getValue())
                    .build();
            ProductAttributeValue savedProductAttributeValue =  productAttributeValueRepo
                    .save(productAttributeValue);
            productAttributeValues.add(savedProductAttributeValue);
        }

        //return product Response
        ProductCreatingResponse productCreatingResponse = new ProductCreatingResponse();
        productCreatingResponse.setId(savedProduct.getId());
        productCreatingResponse.setName(savedProduct.getName());
        productCreatingResponse.setDescription(savedProduct.getDescription());
        productCreatingResponse.setTotalSold(savedProduct.getTotalSold());
        productCreatingResponse.setCategoryId(category.getId());

        // create attribute response
        List<AttributeValueResponse> attributeValueResponses = productAttributeValues.stream()
                .map(productAttributeValue -> {
                    return AttributeValueResponse.builder()
                            .productAttributeValueId(productAttributeValue.getId())
                            .attributeId(productAttributeValue.getCategoryAttribute().getId())
                            .attributeName(productAttributeValue.getCategoryAttribute().getAttribute().getName())
                            .value(productAttributeValue.getValue())
                            .build();
                }).toList();

        productCreatingResponse.setAttributes(attributeValueResponses);

        return productCreatingResponse;
    }
}
