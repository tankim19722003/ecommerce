package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/{categoryId}")
    public List<AttributeResponse> getAllAttributeByCategoryId(
            @PathVariable("categoryId") Long categoryId
    ) {
        return attributeService.getAttributesByCategoryId(categoryId);
    }

}
