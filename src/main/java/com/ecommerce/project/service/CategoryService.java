package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();
    void createCatagory(Category category);

    String deleteCategory(long id);
}
