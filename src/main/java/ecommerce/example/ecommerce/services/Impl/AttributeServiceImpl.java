package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.AttributeRepo;
import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<AttributeResponse> getAllAttributes() {
       List<Attribute> attributes= attributeRepo
                    .findAll();
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

        try {
            attributeRepo.save(attribute);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("The attribute is existing!!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to save");
        }

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

        if (!isAttributeExisting) throw new RuntimeException("Attribute does not found");

        try {
            attributeRepo.deleteById(attributeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Attribute");
        }

    }
}
