package com.pizzeriadonremo.Api_pizzeria.repository.Order;

import com.pizzeriadonremo.Api_pizzeria.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    //get orders in a specific day
    @Query("SELECT s FROM Order s WHERE s.dateOfOrder =:date")
    List<Order> findOrdersByDate(LocalDate date);
}
