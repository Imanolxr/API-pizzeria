package com.pizzeriadonremo.Api_pizzeria.service.Category;

import com.pizzeriadonremo.Api_pizzeria.exception.ResourceNotFoundException;
import com.pizzeriadonremo.Api_pizzeria.model.Category;
import com.pizzeriadonremo.Api_pizzeria.repository.Category.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    private final ICategoryRepository categoryRepo;


    public CategoryService(ICategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public void createCategory(Category newCategory) {
        categoryRepo.save(newCategory);
    }

    @Override
    public Category readCategory(Long category_id) throws ResourceNotFoundException {
        if(!categoryRepo.existsById(category_id)){
            throw new ResourceNotFoundException("Categoria no encontrada con el id: " + category_id, "P-404");
        }
        return categoryRepo.findById(category_id).orElse(null);
    }

    @Override
    public void updateCategory(Long category_id, Category updatedCategory) throws ResourceNotFoundException{
        if(!categoryRepo.existsById(category_id)){
            throw new ResourceNotFoundException("Categoria no encontrada con el id: " + category_id, "P-404");
        }
        updatedCategory.setCategory_id(category_id);
        this.categoryRepo.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Long category_id) throws ResourceNotFoundException{
        if(!categoryRepo.existsById(category_id)){
            throw new ResourceNotFoundException("Categoria no encontrada con el id: " + category_id, "P-404");
        }try {
            categoryRepo.deleteById(category_id);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la categoria: " + e.getMessage());
        }
    }

    @Override
    public List<Category> readAllCategories() {
        return categoryRepo.findAll();
    }
}
