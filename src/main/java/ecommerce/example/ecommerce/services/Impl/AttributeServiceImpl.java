package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.AttributeRepo;
import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepo attributeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AttributeResponse> getAttributesByCategoryId(Long categoryId) {
       List<Attribute> attributes= attributeRepo
                    .findAttributesByCategoryId(categoryId);
        List<AttributeResponse> attributeResponses = attributes.stream().map(
                attribute ->  mapper.map(attribute, AttributeResponse.class)
        ).toList();

        return attributeResponses;

    }

    @Override
    @Transactional
    public AttributeResponse addAttribute(String name) {

        Boolean isAttributeExisting = attributeRepo.existsByName(name);

        if (isAttributeExisting) throw new RuntimeException("Attribute is existing");

        Attribute attribute = new Attribute();
        attribute.setName(name);

        attributeRepo.save(attribute);

        return modelMapper.map(attribute, AttributeResponse.class);

    }

    @Override
    @Transactional
    public List<AttributeResponse> addMultipleAttributes(List<String> names) {

        List<AttributeResponse> attributeResponses = new ArrayList<>();
        for (String name : names) {
            attributeResponses.add(addAttribute(name));
        }

        return attributeResponses;
    }

    @Override
    @Transactional
    public AttributeResponse updateAttribute(Long attributeId, String name) {

        Attribute attribute = attributeRepo.findById(attributeId).orElseThrow(
                () -> new RuntimeException("Attribute does not found")
        );

        attribute.setName(name);

        return modelMapper.map(attribute, AttributeResponse.class);
    }

    @Override
    @Transactional
    public void deleteAttributeId(Long attributeId) {

        boolean isAttributeExisting = attributeRepo.existsById(attributeId);

        if (isAttributeExisting) throw new RuntimeException("Attribute is existing");

        try {
            attributeRepo.deleteById(attributeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Attribute");
        }

    }
}
