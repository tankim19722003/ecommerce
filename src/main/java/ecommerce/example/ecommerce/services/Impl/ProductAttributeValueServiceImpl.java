package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductAttributeValueRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.Repo.SubcategoryAttributeRepo;
import ecommerce.example.ecommerce.dtos.ProductAttributeValueDTO;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductAttributeValue;
import ecommerce.example.ecommerce.models.SubCategoryAttribute;
import ecommerce.example.ecommerce.responses.ProductAttributeValueResponse;
import ecommerce.example.ecommerce.services.ProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

    @Autowired
    private ProductAttributeValueRepo productAttributeValueRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SubcategoryAttributeRepo subcategoryAttributeRepo;

    @Override
    @Transactional
    public ProductAttributeValueResponse createProductAttributeValue(
            ProductAttributeValueDTO productAttributeValueDTO,
            Long productId
    ) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product does not exist!!"));

        return saveProductAttributeValue(productAttributeValueDTO.getSubcategoryAttributeId(), product, productAttributeValueDTO.getValue())
                .toProductAttributeValueResponse();


    }

    @Override
    @Transactional
    public List<ProductAttributeValueResponse> createMultipleProductAttributeValue(
            Long productId,
            List<ProductAttributeValueDTO> productAttributeValueDTOs
    ) {

        List<ProductAttributeValueResponse> productAttributeValueResponses = new ArrayList<>();
        Product product = productRepo.findById(productId)
                .orElseThrow(() ->  new RuntimeException("Product does not found!!"));

        for (ProductAttributeValueDTO productAttributeValueDTO : productAttributeValueDTOs) {
            ProductAttributeValueResponse productAttributeValueResponse = saveProductAttributeValue(productAttributeValueDTO.getSubcategoryAttributeId(), product, productAttributeValueDTO.getValue())
                    .toProductAttributeValueResponse();

            productAttributeValueResponses.add(productAttributeValueResponse);
        }

        return productAttributeValueResponses;
    }

    @Override
    public List<ProductAttributeValueResponse> getAllProductAttributeValue(Long productId) {

        List<ProductAttributeValue> productAttributeValues = productAttributeValueRepo
                .findAllByProductId(productId);

        List<ProductAttributeValueResponse> productAttributeValueResponses = productAttributeValues
                .stream()
                .map(productAttributeValue -> productAttributeValue.toProductAttributeValueResponse())
                .toList();

        return productAttributeValueResponses;
    }

    @Override
    public ProductAttributeValueResponse updateProductAttributeValue(
            ProductAttributeValueDTO productAttributeValueDTO,
            Long productAttributeValueId,
            Long productId,
            Long shopId
    ) {

        ProductAttributeValue productAttributeValue = productAttributeValueRepo
                .findById(productAttributeValueId)
                .orElseThrow(() -> new RuntimeException("Product attribute value does not found!!"));


        // is product belongs to shop
        if (productAttributeValue.getProduct().getShop().getId() != shopId)
            throw new RuntimeException("Product does not belongs to your shop");


        // check is product's attribute value
        if(productAttributeValue.getProduct().getId() != productId)
            throw new RuntimeException("Product attribute value does not belong to product");

        SubCategoryAttribute subCategoryAttribute = subcategoryAttributeRepo
                .findById(productAttributeValueDTO.getSubcategoryAttributeId())
                        .orElseThrow(() ->  new RuntimeException("Subcategory does not exist!"));

        productAttributeValue.setValue(productAttributeValueDTO.getValue());
        productAttributeValue.setSubcategoryAttribute(subCategoryAttribute);

        return productAttributeValueRepo.save(productAttributeValue)
                .toProductAttributeValueResponse();
    }

    @Override
    @Transactional
    public void deleteProductAttributeValue(Long productAttributeValueId, Long shopId) {

        ProductAttributeValue productAttributeValueExisting = productAttributeValueRepo.findById(productAttributeValueId)
                .orElseThrow(() -> new RuntimeException("Product does not found!!"));

        if (productAttributeValueExisting.getProduct().getShop().getId() != shopId)
            throw new RuntimeException("Product does not belongs to your shop");

        productAttributeValueRepo.deleteById(productAttributeValueId);
    }


    @Transactional
    private ProductAttributeValue saveProductAttributeValue(Long subcategoryAttributeId, Product product, String attributeValue) {
        SubCategoryAttribute subCategoryAttribute = subcategoryAttributeRepo.findById(subcategoryAttributeId)
                .orElseThrow(() ->  new RuntimeException("subcategory does not found"));

        ProductAttributeValue productAttributeValue = ProductAttributeValue.builder()
                .product(product)
                .subcategoryAttribute(subCategoryAttribute)
                .value(attributeValue)
                .build();

        try {
            productAttributeValueRepo.save(productAttributeValue);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Product attribute value is existing");
        } catch (RuntimeException e) {
            throw new RuntimeException("failed to save!!");
        }

        return productAttributeValue;
    }


}
