package com.pizzeriadonremo.Api_pizzeria.rest.Controller;

import com.pizzeriadonremo.Api_pizzeria.model.Category;
import com.pizzeriadonremo.Api_pizzeria.service.Category.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    private final ICategoryService categoryServ;

    public CategoryController(ICategoryService categoryServ) {
        this.categoryServ = categoryServ;
    }

    @PostMapping("/newCategory")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category newCategory, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validación de datos de la categoria " , HttpStatus.BAD_REQUEST);
        }
        categoryServ.createCategory(newCategory);
        return new ResponseEntity<>("Categoria creada", HttpStatus.CREATED);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Category> readCategory(@PathVariable Long category_id){
        Category category = categoryServ.readCategory(category_id);
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category updatedCategory, @PathVariable Long category_id, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validacion de datos de la categoria", HttpStatus.BAD_REQUEST);
        }
        categoryServ.updateCategory(category_id, updatedCategory);
        return new ResponseEntity<>("Categoria actualizada", HttpStatus.OK);
    }
    @DeleteMapping("/{category_id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long category_id){
        categoryServ.deleteCategory(category_id);
        return new ResponseEntity<>("Catgoria eliminada", HttpStatus.ACCEPTED);
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<Category>> readAllCategories(){
        List <Category> allCategories = categoryServ.readAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
    }
}
