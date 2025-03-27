package com.pizzeriadonremo.Api_pizzeria.repository.Category;

import com.pizzeriadonremo.Api_pizzeria.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
