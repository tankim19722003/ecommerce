package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.CategoryDTO;
import ecommerce.example.ecommerce.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    CategoryResponse createCategory(CategoryDTO categoryDTO);

    CategoryResponse updateCategory(CategoryDTO categoryDTO, Long categoryId);

    void deleteCategoryById(Long categoryId);


}
