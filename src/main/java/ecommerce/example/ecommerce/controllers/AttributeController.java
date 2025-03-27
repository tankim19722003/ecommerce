package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.AttributeDTO;
import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;


    @PostMapping("/add_attribute")
    public ResponseEntity<?> addAttribute(
        @RequestBody Map<String, String> body
    ) {
        try {
            String attributeName = body.get("attribute_name");
            if (attributeName == null || attributeName.equals("")) throw new Exception("Invalid attribute");

            AttributeResponse attributeResponse = attributeService.addAttribute(attributeName);
            return ResponseEntity.ok(attributeResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }


    @PostMapping("/add_multiple_attribute")
    public ResponseEntity<?> addMultipleAttributes(
        @RequestBody List<String> attributeNames
    ){
        try {
            List<AttributeResponse> attributeResponses = attributeService.addMultipleAttributes(attributeNames);
            return ResponseEntity.ok(attributeResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
            );
        }

    }

    @PutMapping("")
    public ResponseEntity<?> updateAttribute(
            @RequestBody AttributeDTO attributeDTO
    ) {
        try {
            AttributeResponse attributeResponse = attributeService
                    .updateAttribute(attributeDTO.getAttributeId(), attributeDTO.getName());
            return ResponseEntity.ok(attributeResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(EResponse
                            .builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("")
    public List<AttributeResponse> getAllAttributeByCategoryId() {
        return attributeService.getAllAttributes();
    }

    @DeleteMapping("/{attributeId}")
    public ResponseEntity<?> deleteAttributeById(
            @PathVariable("attributeId") Long id
    ) {
        try {
            attributeService.deleteAttributeId(id);
            return ResponseEntity.noContent().build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }

}
