package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.CategoryRepo;
import ecommerce.example.ecommerce.dtos.CategoryDTO;
import ecommerce.example.ecommerce.models.Category;
import ecommerce.example.ecommerce.responses.CategoryResponse;
import ecommerce.example.ecommerce.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CategoryResponse> getAllCategory() {

        List<Category> categories = categoryRepo.findAll();

        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> mapper.map(category, CategoryResponse.class))
                .toList();

        return categoryResponses;
    }


    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

        categoryRepo.save(category);


        return mapper.map(category, CategoryResponse.class);

    }

    @Override
    public CategoryResponse updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        // Check if category exists
        Category existingCategory = categoryRepo.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

            // Update category with new values from categoryDTO
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        // Save the updated category
        categoryRepo.save(existingCategory);

        return mapper.map(existingCategory, CategoryResponse.class);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        boolean isExistingCategory = categoryRepo.existsById(categoryId);

        if (!isExistingCategory)
            throw new RuntimeException("Category does not existing!");
        categoryRepo.deleteById(categoryId);
    }



}
