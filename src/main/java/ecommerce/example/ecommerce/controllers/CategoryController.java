package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.dtos.CategoryDTO;
import ecommerce.example.ecommerce.responses.CategoryResponse;
import ecommerce.example.ecommerce.responses.EResponse;
import ecommerce.example.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get_all_categories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/create")
    public CategoryResponse createCategory(
            @RequestBody CategoryDTO categoryDTO
    ) {
        return categoryService.createCategory(categoryDTO);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable long categoryId,
            @RequestBody CategoryDTO categoryDTO
    ) {

        try {
            CategoryResponse categoryResponse = categoryService.updateCategory(categoryDTO, categoryId);
            return ResponseEntity.ok(categoryResponse);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(
            @PathVariable("categoryId") Long categoryId
    ) {

        try {
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    EResponse.builder()
                            .name("ERROR")
                            .message(e.getMessage())
                            .build()
            );
        }
    }
}
