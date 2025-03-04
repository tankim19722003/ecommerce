package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategory();
}
