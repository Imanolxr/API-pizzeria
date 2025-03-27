package com.pizzeriadonremo.Api_pizzeria.service.Order;

import com.pizzeriadonremo.Api_pizzeria.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IOrderService {

    //new order
    public void newOrder(Order newOrder);

    //delete order
    public void deleteOrder(Long order_id);

    //read sale
    public Order readOrder(Long order_id);

    // modify order
    public void updateOrder(Long order_id, Order updatedOrder);

    // read all orders
    public List<Order> readAllOrders();

    //read orders in a specific day
    public List<Order> allOrdersInADay(LocalDate dateOfOrder);


}
