package com.pizzeriadonremo.Api_pizzeria.service.Product;

import com.pizzeriadonremo.Api_pizzeria.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    //create Product
    public void createProduct (Product newProduct);

    //read Product
    public Product readProduct(Long product_id);

    //update Product
    public void updateProduct(Long product_id, Product updatedProduct);

    //delete Product
    public void deleteProduct(Long product_id);

    //read all products
    public List<Product> readAllProducts();

    //read all products in a category
    public List<Product> allProductsByCategory(Long category_id);

    //reduce stock after a sale
    public void reduceStock(Long product_id, int quantityInOrder);
}
