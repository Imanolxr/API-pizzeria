package com.pizzeriadonremo.Api_pizzeria;

import com.pizzeriadonremo.Api_pizzeria.model.Category;
import com.pizzeriadonremo.Api_pizzeria.repository.Category.ICategoryRepository;
import com.pizzeriadonremo.Api_pizzeria.service.Category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepo;

    @InjectMocks
    private CategoryService categoryServ;

    private Category expectedCategory;

    @BeforeEach
    public void setUp(){

        expectedCategory = new Category(1L, "pizzas");
            }

    @Test
    public void testCreateCategory(){
        when(categoryRepo.save(Mockito.any(Category.class))).thenReturn(expectedCategory);
        categoryServ.createCategory(expectedCategory);
        verify(categoryRepo, times(1)).save(expectedCategory);
    }

    @Test
    public void testReadCategory(){
        when(categoryRepo.existsById(1L)).thenReturn(true);
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(expectedCategory));
        final Category resultCategory = categoryServ.readCategory(1L);
        assertEquals(expectedCategory, resultCategory);
    }

    @Test
    public void testUpdateCategory(){
        when(categoryRepo.existsById(1L)).thenReturn(true);
        Category updatedCategory = new Category(1L, "empanadas");
        categoryServ.updateCategory(expectedCategory.getCategory_id(), updatedCategory);

        verify(categoryRepo, times(1)).save(updatedCategory);
    }

    @Test
    public void testDeleteCategory(){
        when(categoryRepo.existsById(1L)).thenReturn(true);
        doNothing().when(categoryRepo).deleteById(expectedCategory.getCategory_id());
        categoryServ.deleteCategory(expectedCategory.getCategory_id());

        verify(categoryRepo, times(1)).deleteById(expectedCategory.getCategory_id());
    }

    @Test
    public void testReadCategoryList(){
        List<Category> categoryList = Arrays.asList(
                new Category(2L, "bebidas"),
                new Category(3L,"postres")
        );
        when(categoryRepo.findAll()).thenReturn(categoryList);
        List<Category> result = categoryServ.readAllCategories();
        assertEquals(categoryList, result);
        verify(categoryRepo, times(1)).findAll();
    }


}
