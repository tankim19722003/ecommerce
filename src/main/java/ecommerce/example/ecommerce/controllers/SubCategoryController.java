package ecommerce.example.ecommerce.controllers;


import ecommerce.example.ecommerce.dtos.SubCategoryDTO;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.responses.SubCategoryInListResponse;
import ecommerce.example.ecommerce.responses.SubCategoryResponse;
import ecommerce.example.ecommerce.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/sub_category")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    // Endpoint to create a new subcategory
    @PostMapping("")
    public ResponseEntity<?> createSubCategory(
            @RequestBody SubCategoryDTO subCategoryDTO
    ) {

        try {
            SubCategoryResponse createdSubCategory = subCategoryService.createCategory(subCategoryDTO);
            return new ResponseEntity<>(createdSubCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST); // Return error if creation failed
        }

    }

    // Endpoint to update an existing subcategory
    @PutMapping("/{subCategoryId}")
    public ResponseEntity<?> updateSubCategory(
            @PathVariable Long subCategoryId,
            @RequestBody SubCategoryDTO subCategoryDTO)
    {

        try {
            SubCategoryResponse updatedSubCategory = subCategoryService.updateCategory(subCategoryDTO, subCategoryId);
            return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST); // Return error if creation failed
        }
    }

    // Endpoint to delete a subcategory by ID
    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable Long subCategoryId) {

        try {
            subCategoryService.deleteSubCategory(subCategoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if deletion was successful
        } catch (Exception e) {
            return new ResponseEntity<>(
                    EResponse.builder()
                            .name("Error")
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST); // Return error if creation failed
        }

    }

    // Endpoint to get all subcategories
    @GetMapping
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> subCategoryResponses = subCategoryService.getAllSubcategories();

        if (subCategoryResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no subcategories found
        } else {
            return new ResponseEntity<>(subCategoryResponses, HttpStatus.OK);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<SubCategoryInListResponse>> getAllSubCategories(
            @PathVariable("categoryId") long categoryId
    ) {
        List<SubCategoryInListResponse> subCategoryResponses = subCategoryService
                .getAllSubcategoriesByCategoryId(categoryId);

        if (subCategoryResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no subcategories found
        } else {
            return new ResponseEntity<>(subCategoryResponses, HttpStatus.OK);
        }
    }



}
