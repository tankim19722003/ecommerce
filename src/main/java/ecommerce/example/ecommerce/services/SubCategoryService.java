package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.Repo.SubCategoryRepo;
import ecommerce.example.ecommerce.dtos.CategoryDTO;
import ecommerce.example.ecommerce.dtos.SubCategoryDTO;
import ecommerce.example.ecommerce.models.SubCategory;
import ecommerce.example.ecommerce.responses.SubCategoryInListResponse;
import ecommerce.example.ecommerce.responses.SubCategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponse createCategory(SubCategoryDTO subCategoryDTO);

    SubCategoryResponse updateCategory(SubCategoryDTO subCategoryDTO, Long subCategoryId);

    void deleteSubCategory(Long subCategoryId);

    List<SubCategoryResponse> getAllSubcategories();

    List<SubCategoryInListResponse> getAllSubcategoriesByCategoryId(Long categoryId);
}
