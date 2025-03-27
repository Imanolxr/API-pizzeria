package com.pizzeriadonremo.Api_pizzeria.rest.Controller;

import com.pizzeriadonremo.Api_pizzeria.model.Product;
import com.pizzeriadonremo.Api_pizzeria.service.Product.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final IProductService productServ;

    public ProductController(IProductService productServ) {
        this.productServ = productServ;
    }

    @PostMapping("/newProduct")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product newProduct, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>("Error en la validacion de los datos del nuevo producto", HttpStatus.BAD_REQUEST);
        }
        productServ.createProduct(newProduct);
        return new ResponseEntity<>("Producto creado", HttpStatus.CREATED);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> readProduct(@PathVariable Long product_id){
        Product product = productServ.readProduct(product_id);
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long product_id, @RequestBody Product updatedProduct){
        productServ.updateProduct(product_id, updatedProduct);
        return new ResponseEntity<>("Producto Modificado", HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteMapping(@PathVariable Long product_id){
        productServ.deleteProduct(product_id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.ACCEPTED);
    }

    @GetMapping("/showAllProducts")
    public ResponseEntity<List<Product>> readAllProducts(){
        List<Product> allProductsList = productServ.readAllProducts();
        return new ResponseEntity<>(allProductsList, HttpStatus.OK);
    }

    @GetMapping("/byCategory/{category_id}")
    public ResponseEntity<List<Product>> readProductsByCategory(@PathVariable Long category_id){
        List<Product> productsByCategoryList = productServ.allProductsByCategory(category_id);
        return new ResponseEntity<>(productsByCategoryList, HttpStatus.OK);
    }



}
