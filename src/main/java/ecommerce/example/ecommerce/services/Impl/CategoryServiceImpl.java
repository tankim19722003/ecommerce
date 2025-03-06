package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.CategoryRepo;
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

}
