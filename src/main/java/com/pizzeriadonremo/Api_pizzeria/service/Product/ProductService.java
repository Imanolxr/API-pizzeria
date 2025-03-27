package com.pizzeriadonremo.Api_pizzeria.service.Product;

import com.pizzeriadonremo.Api_pizzeria.exception.ResourceNotFoundException;
import com.pizzeriadonremo.Api_pizzeria.model.Product;
import com.pizzeriadonremo.Api_pizzeria.repository.Product.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private final IProductRepository productRepo;

    public ProductService(IProductRepository productRepo) {
        this.productRepo = productRepo;
    }


    @Transactional
    @Override
    public void createProduct(Product newProduct) {
        productRepo.save(newProduct);
    }

    @Transactional
    @Override
    public Product readProduct(Long product_id) throws ResourceNotFoundException {
        if(!productRepo.existsById(product_id)){
            throw new ResourceNotFoundException("Producto no encontrado con el id: " + product_id , "P-404");
        }
        return productRepo.findById(product_id).orElseThrow();
    }

    @Transactional
    @Override
    public void updateProduct(Long product_id, Product updatedProduct) throws ResourceNotFoundException{
        if(!productRepo.existsById(product_id)){
            throw new ResourceNotFoundException("Producto no encontrado con el id: " + product_id, "P-404");
        }
        updatedProduct.setProduct_id(product_id);
        this.productRepo.save(updatedProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(Long product_id) throws ResourceNotFoundException{
        if(!productRepo.existsById(product_id)){
            throw new ResourceNotFoundException("Producto no encontrado con el id: " + product_id, "P-404");
        }
        productRepo.deleteById(product_id);
        productRepo.flush();
    }

    @Override
    public List<Product> readAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> allProductsByCategory(Long category_id) {
        return productRepo.findByCategoryId(category_id);
    }

    @Override
    public void reduceStock(Long product_id, int quantityInOrder) {
       Product updatedProduct =  productRepo.findById(product_id).orElseThrow();
       updatedProduct.setAvailableQuantity(updatedProduct.getAvailableQuantity() - quantityInOrder);
       updateProduct(product_id, updatedProduct);
    }
}
