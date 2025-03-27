package com.pizzeriadonremo.Api_pizzeria.service.Category;

import com.pizzeriadonremo.Api_pizzeria.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {

    //create category
    public void createCategory(Category newCategory);

    //read category
    public Category readCategory(Long category_id);

    //update Category
    public void updateCategory(Long category_id, Category updatedCategory);

    //delete Category
    public void deleteCategory(Long category_id);

    //read all categories
    public List<Category> readAllCategories();
}
