package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.SubcategoryAttributeDTO;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeListResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeResponse;

import java.util.List;

public interface SubcategoryAttributeService {

    SubcategoryAttributeListResponse getAllAttributeBySubcategoryId(
            Long subcategoryId
    );

    SubcategoryAttributeResponse addAttributeForSubcategory(
            SubcategoryAttributeDTO subcategoryAttributeDTO
    );

    SubcategoryAttributeListResponse addMultipleAttributeForSubcategory(
           Long subcategoryId, List<Long> attributeIds
    );

    SubcategoryAttributeResponse updateSubcategoryAttribute(
            Long subcategoryAttributeId,
            SubcategoryAttributeDTO subcategoryAttributeDTO
    );

    void deleteSubcategoryAttribute(
            Long subcategoryAttributeId
    );

}
