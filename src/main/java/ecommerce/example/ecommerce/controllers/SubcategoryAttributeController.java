package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.SubcategoryAttributeDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeListResponse;
import ecommerce.example.ecommerce.responses.SubcategoryAttributeResponse;
import ecommerce.example.ecommerce.services.SubcategoryAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/subcategory_attribute")
public class SubcategoryAttributeController {

    @Autowired
    private SubcategoryAttributeService subcategoryAttributeService;

    @PostMapping("/add_one")
    public ResponseEntity<?> addSubcategoryAttribute(
        @RequestBody SubcategoryAttributeDTO subcategoryAttributeDTO
    ) {

        try {
            SubcategoryAttributeResponse subcategoryAttributeResponse = subcategoryAttributeService
                    .addAttributeForSubcategory(subcategoryAttributeDTO);
            return ResponseEntity.ok(subcategoryAttributeResponse);
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

    @PostMapping("/add_multiple/{subcategoryId}")
    public ResponseEntity<?> addMultipleAttributeForSubcategory(
            @PathVariable("subcategoryId") Long subcategoryId,
            @RequestBody List<Long > attributeIds
    ) {
        try {
            SubcategoryAttributeListResponse subcategoryAttributeListResponse = subcategoryAttributeService
                    .addMultipleAttributeForSubcategory(subcategoryId, attributeIds);

            return ResponseEntity.ok(subcategoryAttributeListResponse);
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

    @GetMapping("/{subcategoryId}")
    public ResponseEntity<?> getAllAttributeBySubcategoryId(
        @PathVariable("subcategoryId") Long subcategoryId
    ) {

        try {
            SubcategoryAttributeListResponse subcategoryAttributeListResponse =
                    subcategoryAttributeService.getAllAttributeBySubcategoryId(subcategoryId);

            return ResponseEntity.ok(subcategoryAttributeListResponse);
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


    @PutMapping("/{subcategoryAttributeId}")
    public ResponseEntity<?> updateSubcategoryAttribute(
        @PathVariable("subcategoryAttributeId") Long subcategoryAttributeId,
        @RequestBody SubcategoryAttributeDTO subcategoryAttributeDTO
    ) {
        try {
            SubcategoryAttributeResponse subcategoryAttributeListResponse = subcategoryAttributeService
                    .updateSubcategoryAttribute(subcategoryAttributeId, subcategoryAttributeDTO);

            return ResponseEntity.ok(subcategoryAttributeListResponse);
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

    @DeleteMapping("/{subcategoryAttributeId}")
    public ResponseEntity<?> deleteSubcategoryAttribute(
            @PathVariable("subcategoryAttributeId") Long subcategoryAttributeId
    ) {

        try {
            subcategoryAttributeService
                    .deleteSubcategoryAttribute(subcategoryAttributeId);

            return ResponseEntity.noContent().build();
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
}
