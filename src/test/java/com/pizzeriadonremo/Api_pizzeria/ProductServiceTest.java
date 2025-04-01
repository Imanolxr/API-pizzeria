package com.pizzeriadonremo.Api_pizzeria;

import com.pizzeriadonremo.Api_pizzeria.model.Category;
import com.pizzeriadonremo.Api_pizzeria.model.Product;
import com.pizzeriadonremo.Api_pizzeria.repository.Product.IProductRepository;
import com.pizzeriadonremo.Api_pizzeria.service.Product.ProductService;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private IProductRepository productRepo;

    @InjectMocks
    private ProductService productServ;
    private Product expectedProduct;

    @BeforeEach
    public void setUp(){
        expectedProduct = new Product(11L, "pizza margarita", 15.00, 100);
    }

    @Test
    public void testCreateProduct(){
        when(productRepo.save(Mockito.any(Product.class))).thenReturn(expectedProduct);
        productServ.createProduct(expectedProduct);
        verify(productRepo, times(1)).save(expectedProduct);
    }

    @Test
    public void testReadProduct(){
        when(productRepo.existsById(11L)).thenReturn(true);
        when(productRepo.findById(11L)).thenReturn(Optional.of(expectedProduct));
        final Product resultProduct = productServ.readProduct(11L);
        assertEquals(expectedProduct, resultProduct);
    }

    @Test
    public void testUpdateProduct(){
        when(productRepo.existsById(11L)).thenReturn(true);
        Product updatedProduct = new Product(11L, "pizza quatro formaggi", 17.00, 100);
        productServ.updateProduct(expectedProduct.getProduct_id(), updatedProduct);

        verify(productRepo, times(1)).save(updatedProduct);

    }

    @Test
    public void testDeleteProduct(){
        when(productRepo.existsById(11L)).thenReturn(true);
        doNothing().when(productRepo).deleteById(expectedProduct.getProduct_id());
        productServ.deleteProduct(expectedProduct.getProduct_id());

        verify(productRepo, times(1)).deleteById(expectedProduct.getProduct_id());
    }

    @Test
    public void testReadAllProducts(){
        List<Product> productList = Arrays.asList(
                new Product(12L, "pizza diavola", 17.00, 100),
                new Product(12L, "pizza napoli", 13.00, 100)
        );
        when(productRepo.findAll()).thenReturn(productList);
        List<Product> result = productServ.readAllProducts();
        assertEquals(productList, result);
        verify(productRepo, times(1)).findAll();
    }

    @Test
    public void testProductsByCategory(){

        Category category = new Category(1L, "pizzas");
        Product product1 = new Product(11L, "pizza margarita", 15.00, 100, category);
        Product product2 = new Product(12L, "pizza diavoletta", 18.00, 50, category);
        List<Product> productList = Arrays.asList(product1, product2);

        when(productRepo.findByCategoryId(1L)).thenReturn(productList);

        List<Product> result = productServ.allProductsByCategory(1L);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("pizza margarita", result.get(0).getProductName());
        assertEquals("pizza diavoletta",result.get(1).getProductName());

        verify(productRepo, times(1)).findByCategoryId(1L);

    }

    @Test
    public void testReduceStock(){
        when(productRepo.existsById(11L)).thenReturn(true);
        when(productRepo.findById(11L)).thenReturn(Optional.of(expectedProduct));

        productServ.reduceStock(11L, 10);

        assertEquals(90, expectedProduct.getAvailableQuantity());

        verify(productRepo, times(1)).save(any(Product.class));

    }

}
