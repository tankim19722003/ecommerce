package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ProductAttributeValueRepo;
import ecommerce.example.ecommerce.Repo.ProductRepo;
import ecommerce.example.ecommerce.Repo.QuantityRepo;
import ecommerce.example.ecommerce.dtos.QuantityDTO;
import ecommerce.example.ecommerce.dtos.QuantityDTOList;
import ecommerce.example.ecommerce.models.Product;
import ecommerce.example.ecommerce.models.ProductAttributeValue;
import ecommerce.example.ecommerce.models.Quantity;
import ecommerce.example.ecommerce.responses.AttributeValueResponse;
import ecommerce.example.ecommerce.responses.QuantityResponse;
import ecommerce.example.ecommerce.services.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductAttributeValueRepo productAttributeValueRepo;

    @Autowired
    private QuantityRepo quantityRepo;

    @Override
    public void createAttributeQuantity(QuantityDTOList quantityDTOList) throws Exception {


        Product product = productRepo.findById(quantityDTOList.getProductId()).orElseThrow(
                () -> new RuntimeException("Product does not found")
        );
        for (QuantityDTO quantityDTO : quantityDTOList.getQuantityDTOList()) {

            ProductAttributeValue firstProductAttributeValue = productAttributeValueRepo
                    .findById(quantityDTO.getFirstProductAttributeValueId())
                    .orElseThrow(() -> new RuntimeException("First Product attribute value does not found"));

            if (!Objects.equals(firstProductAttributeValue.getProduct().getId(), product.getId())) {
                throw new Exception("Invalid First attribute value id " + firstProductAttributeValue.getId());
            }

            ProductAttributeValue secondProductAttributeValue = productAttributeValueRepo
                    .findById(quantityDTO.getSecondProductAttributeValueId())
                    .orElseThrow(() -> new RuntimeException("Second Product attribute value does not found"));

            if (!Objects.equals(secondProductAttributeValue.getProduct().getId(), product.getId())) {
                throw new Exception("Invalid second attribute value id " + secondProductAttributeValue.getId());
            }

            try {
                Quantity quantity = Quantity.builder()
                        .product(product)
                        .firstProductAttributeValue(firstProductAttributeValue)
                        .secondProductAttributeValue(secondProductAttributeValue)
                        .quantity(quantityDTO.getQuantity())
                        .build();
                quantityRepo.save(quantity);
            }catch (DataIntegrityViolationException e) {
                throw new Exception("attribute_value_id1: "+ quantityDTO.getFirstProductAttributeValueId()+",attribute_value_id2: " + quantityDTO.getSecondProductAttributeValueId()+ " are" +
                        " existing in quantity table");
            } catch (Exception e) {
                throw new Exception("Failed to save!!");
            }
        }

    }

    @Override
    public List<QuantityResponse> getAllAttributeQuantityById(Long productId) {

        List<Quantity> quantities = quantityRepo.findAllByProductId(productId);

        return quantities.stream().map(quantity -> {
            AttributeValueResponse firstAttributeValueResponse = quantity.getFirstProductAttributeValue().toProductAttributeResponse();
            AttributeValueResponse secondAttributeValueResponse = quantity.getSecondProductAttributeValue().toProductAttributeResponse();
            return QuantityResponse.builder()
                    .id(quantity.getId())
                    .firstAttributeValue(firstAttributeValueResponse)
                    .secondAttributeValue(secondAttributeValueResponse)
                    .quantity(quantity.getQuantity())
                    .build();
        }).toList();

    }
}
