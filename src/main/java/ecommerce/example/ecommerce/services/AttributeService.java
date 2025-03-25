package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;

import java.util.List;

public interface AttributeService {
    List<AttributeResponse> getAttributesByCategoryId(Long categoryId);

    AttributeResponse addAttribute(String name);

    List<AttributeResponse> addMultipleAttributes(List<String> names);

    AttributeResponse updateAttribute(Long attributeId, String name);

    void deleteAttributeId(Long attributeId);
}
