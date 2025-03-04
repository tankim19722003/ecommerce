package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;

import java.util.List;

public interface AttributeService {
    List<AttributeResponse> getAttributesByCategoryId(Long categoryId);
}
