package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.CategoryRepo;
import ecommerce.example.ecommerce.Repo.SubCategoryRepo;
import ecommerce.example.ecommerce.dtos.SubCategoryDTO;
import ecommerce.example.ecommerce.models.Category;
import ecommerce.example.ecommerce.models.SubCategory;
import ecommerce.example.ecommerce.responses.CategoryResponse;
import ecommerce.example.ecommerce.responses.SubCategoryInListResponse;
import ecommerce.example.ecommerce.responses.SubCategoryResponse;
import ecommerce.example.ecommerce.services.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SubCategoryResponse createCategory(SubCategoryDTO subCategoryDTO) {

        // check category existing
        Category category = categoryRepo.findById(subCategoryDTO.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );


        // Create a new SubCategory entity
        SubCategory subCategory = SubCategory.builder()
                .category(category)
                .name(subCategoryDTO.getName())
                .description(subCategoryDTO.getDescription())
                .build();

        // Save the subcategory to the database
        subCategoryRepo.save(subCategory);

        // Map to SubCategoryResponse and return
        SubCategoryResponse response = mapper.map(subCategory, SubCategoryResponse.class);

        response.setCategoryResponse(mapper.map(category, CategoryResponse.class));

        return response;
    }

    @Override
    public SubCategoryResponse updateCategory(SubCategoryDTO subCategoryDTO, Long subCategoryId) {
        // Retrieve the subcategory by ID
        SubCategory existingSubCategory = subCategoryRepo.findById(subCategoryId).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );

        // check category existing
        Category category = categoryRepo.findById(subCategoryDTO.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category does not found")
        );


        // Update the fields of the existing subcategory
        existingSubCategory.setName(subCategoryDTO.getName());
        existingSubCategory.setDescription(subCategoryDTO.getDescription());
        existingSubCategory.setCategory(category);

        // Save the updated subcategory
        subCategoryRepo.save(existingSubCategory);

        // Map to SubCategoryResponse and return
        SubCategoryResponse response = mapper.map(existingSubCategory, SubCategoryResponse.class);
        response.setCategoryResponse(mapper.map(category, CategoryResponse.class));

        return response;
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {
        // Check if the subcategory exists
        boolean isSubCategoryExsting = subCategoryRepo.existsById(subCategoryId);

        if (isSubCategoryExsting) {
            categoryRepo.deleteById(subCategoryId);
        } else {
            throw  new RuntimeException("Category does not found");
        }

    }

    @Override
    public List<SubCategoryResponse> getAllSubcategories() {
        // Fetch all subcategories from the repository
        List<SubCategory> subCategories = subCategoryRepo.findAll();

        return subCategories.stream()
                .map(subCategory -> {
                    // Map the subCategory to SubCategoryResponse
                    SubCategoryResponse response = mapper.map(subCategory, SubCategoryResponse.class);

                    // Manually set the categoryResponse field (Assuming a CategoryResponse object is required)
                    // You need to ensure that the categoryResponse is correctly set. Here, we'll assume it needs to be fetched.
                    response.setCategoryResponse(mapper.map(subCategory.getCategory(), CategoryResponse.class));

                    return response;
                }).collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryInListResponse> getAllSubcategoriesByCategoryId(Long categoryId) {
        List<SubCategory> subCategories =  subCategoryRepo.getSubCategoriesByCategoryId(categoryId);

        return subCategories.stream()
                .map(subCategory -> {
                    // Map the subCategory to SubCategoryResponse
                    SubCategoryInListResponse response = mapper.map(subCategory, SubCategoryInListResponse.class);

                    // Manually set the categoryResponse field (Assuming a CategoryResponse object is required)

                    return response;
                }).collect(Collectors.toList());
    }
}
