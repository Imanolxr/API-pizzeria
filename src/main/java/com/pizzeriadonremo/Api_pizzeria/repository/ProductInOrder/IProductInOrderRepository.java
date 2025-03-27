package com.pizzeriadonremo.Api_pizzeria.repository.ProductInOrder;

import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
