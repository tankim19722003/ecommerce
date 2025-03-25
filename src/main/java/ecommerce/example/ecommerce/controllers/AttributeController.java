package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.models.Attribute;
import ecommerce.example.ecommerce.responses.AttributeResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;


    @PostMapping("/add_attribute")
    public ResponseEntity<?> addAttribute(
        @RequestParam("attributeName") String attributeName
    ) {
        try {
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
        @RequestParam("attributeId") Long attributeId,
        @RequestParam("name") String name
    ) {
        try {
            AttributeResponse attributeResponse = attributeService.updateAttribute(attributeId, name);
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

    @GetMapping("/{categoryId}")
    public List<AttributeResponse> getAllAttributeByCategoryId(
            @PathVariable("categoryId") Long categoryId
    ) {
        return attributeService.getAttributesByCategoryId(categoryId);
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
