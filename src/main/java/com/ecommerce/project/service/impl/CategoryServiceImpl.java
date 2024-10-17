package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCatagory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(long categoryId) {

        Category category = categories.stream()
                .filter(c -> c.getCategoryId()==(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));


        categories.remove(category);

        return "Category deleted successfully";

    }

    @Override
    public Category updateCategory(Category category, long categoryId) {
        Optional<Category> optionalCategory  = categories.stream()
                .filter(c -> c.getCategoryId()==(categoryId))
                .findFirst();

        if(optionalCategory.isPresent())
        {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }


}
