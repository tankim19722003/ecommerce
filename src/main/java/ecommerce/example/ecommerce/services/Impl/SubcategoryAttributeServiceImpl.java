package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.AttributeRepo;
import ecommerce.example.ecommerce.Repo.SubCategoryRepo;
import ecommerce.example.ecommerce.Repo.SubcategoryAttributeRepo;
import ecommerce.example.ecommerce.dtos.SubcategoryAttributeDTO;
import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.models.SubCategory;
import ecommerce.example.ecommerce.models.SubCategoryAttribute;
import ecommerce.example.ecommerce.responses.AttributeSubIdResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeListResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeResponse;
import ecommerce.example.ecommerce.services.SubcategoryAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubcategoryAttributeServiceImpl implements SubcategoryAttributeService {

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    @Autowired
    private SubcategoryAttributeRepo subcategoryAttributeRepo;

    @Override
    public SubcategoryAttributeListResponse getAllAttributeBySubcategoryId(Long subcategoryId) {

        SubCategory subCategory = subCategoryRepo
                .findById(subcategoryId)
                .orElseThrow(() ->  new RuntimeException("Subcategory does not found"));

        List<SubCategoryAttribute> subCategoryAttributes = subcategoryAttributeRepo
                .findBySubCategoryId(subcategoryId);

        SubcategoryAttributeListResponse subcategoryAttributeListResponse = new SubcategoryAttributeListResponse();

        subcategoryAttributeListResponse.setSubcategoryId(subcategoryId);
        subcategoryAttributeListResponse.setSubcategoryValue(subCategory.getName());

        if(subCategoryAttributes.isEmpty())
            throw new RuntimeException("Subcategory does not have any attribute");

        for (SubCategoryAttribute subCategoryAttribute : subCategoryAttributes) {

            AttributeSubIdResponse attributeSubIdResponse = new AttributeSubIdResponse();
            subcategoryAttributeListResponse
                    .addAttributeSubIdResponse(subCategoryAttribute.toAttributeSubIdResponse());
        }

        return subcategoryAttributeListResponse;

    }

    @Override
    @Transactional
    public SubcategoryAttributeResponse addAttributeForSubcategory(SubcategoryAttributeDTO subcategoryAttributeDTO) {

        SubCategory subCategory = subCategoryRepo.findById(subcategoryAttributeDTO.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory does not found"));

        return saveSubcategoryAttribute(subcategoryAttributeDTO.getAttributeId(), subCategory)
                .toSubcategoryAttributeResponse();

    }

    @Transactional
    private SubCategoryAttribute saveSubcategoryAttribute(Long attributeId, SubCategory subCategory) {
        Attribute attribute = attributeRepo.findById(attributeId)
                .orElseThrow(() -> new RuntimeException("Attribute does not found"));

        SubCategoryAttribute subCategoryAttribute = SubCategoryAttribute.builder()
                .attribute(attribute)
                .subCategory(subCategory)
                .build();

        try {
            subcategoryAttributeRepo.save(subCategoryAttribute);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid input!!");
        }

        return subCategoryAttribute;
    }
    @Override
    public SubcategoryAttributeListResponse addMultipleAttributeForSubcategory(Long subcategoryId, List<Long> attributeIds) {

        SubCategory subCategory = subCategoryRepo.findById(subcategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory does not found"));

        SubcategoryAttributeListResponse subcategoryAttributeListResponse = new SubcategoryAttributeListResponse();

        // set subvalue
        subcategoryAttributeListResponse.setSubcategoryId(subcategoryId);
        subcategoryAttributeListResponse.setSubcategoryValue(subCategory.getName());


        // save the subcategory attribute
        for(Long attributeId : attributeIds){
            SubCategoryAttribute subCategoryAttribute = saveSubcategoryAttribute(attributeId, subCategory);
            subcategoryAttributeListResponse
                    .addAttributeSubIdResponse(subCategoryAttribute.toAttributeSubIdResponse());
        }

        return subcategoryAttributeListResponse;
    }



    @Override
    public SubcategoryAttributeResponse updateSubcategoryAttribute(Long subcategoryAttributeId, SubcategoryAttributeDTO subcategoryAttributeDTO) {

        SubCategoryAttribute subCategoryAttribute = subcategoryAttributeRepo
                .findById(subcategoryAttributeId)
                .orElseThrow(() -> new RuntimeException("Subcategory attribute does not found"));

        Attribute attribute = attributeRepo.findById(subcategoryAttributeDTO.getAttributeId())
                .orElseThrow(() -> new RuntimeException("Attribute does not exist"));

        SubCategory subCategory = subCategoryRepo.findById(subcategoryAttributeDTO.getSubcategoryId())
                .orElseThrow(() ->  new RuntimeException("Subcategory does not found"));

        subCategoryAttribute.setAttribute(attribute);
        subCategoryAttribute.setSubCategory(subCategory);

        subcategoryAttributeRepo.save(subCategoryAttribute);

        return subCategoryAttribute.toSubcategoryAttributeResponse();
    }

    @Override
    @Transactional
    public void deleteSubcategoryAttribute(Long subcategoryAttributeId) {

        boolean isSubcategoryExisting = subcategoryAttributeRepo
                .existsById(subcategoryAttributeId);

        if (!isSubcategoryExisting)
            throw new RuntimeException("Subcategory attribute does not exist!!");

        subcategoryAttributeRepo.deleteById(subcategoryAttributeId);
    }
}
