package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.AttributeRepo;
import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepo attributeRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AttributeResponse> getAttributesByCategoryId(Long categoryId) {
       List<Attribute> attributes= attributeRepo
                    .findAttributesByCategoryId(categoryId);
        List<AttributeResponse> attributeResponses = attributes.stream().map(
                attribute ->  mapper.map(attribute, AttributeResponse.class)
        ).toList();

        return attributeResponses;

    }
}
