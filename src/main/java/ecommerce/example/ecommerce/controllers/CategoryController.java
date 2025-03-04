package ecommerce.example.ecommerce.controllers;

import ecommerce.example.ecommerce.responses.CategoryResponse;
import ecommerce.example.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
