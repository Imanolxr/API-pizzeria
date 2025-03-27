package com.pizzeriadonremo.Api_pizzeria.repository.Product;

import com.pizzeriadonremo.Api_pizzeria.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    // find all products by a category
    @Query("SELECT p FROM Product p WHERE p.category.category_id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);



}
