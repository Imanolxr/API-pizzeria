package com.pizzeriadonremo.Api_pizzeria.service.Order;

import com.pizzeriadonremo.Api_pizzeria.exception.InsufficientStockException;
import com.pizzeriadonremo.Api_pizzeria.exception.ResourceNotFoundException;
import com.pizzeriadonremo.Api_pizzeria.model.Order;
import com.pizzeriadonremo.Api_pizzeria.model.Product;
import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import com.pizzeriadonremo.Api_pizzeria.repository.Order.IOrderRepository;
import com.pizzeriadonremo.Api_pizzeria.repository.Product.IProductRepository;
import com.pizzeriadonremo.Api_pizzeria.repository.ProductInOrder.IProductInOrderRepository;
import com.pizzeriadonremo.Api_pizzeria.service.Product.IProductService;
import com.pizzeriadonremo.Api_pizzeria.service.Product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    private final IOrderRepository orderRepo;
    private final IProductInOrderRepository productInOrderRepo;
    private final IProductRepository productRepo;
    private final IProductService productServ;

    public OrderService(IOrderRepository orderRepo, IProductInOrderRepository productInOrderRepo, IProductRepository productRepo, ProductService productServ) {
        this.orderRepo = orderRepo;
        this.productInOrderRepo = productInOrderRepo;
        this.productRepo = productRepo;
        this.productServ = productServ;
    }


    @Override
    @Transactional
    public void newOrder(Order newOrder) {
        newOrder.setDateOfOrder(LocalDate.now());

        //verificar que hay productos en la orden
        for (ProductInOrder productInOrder : newOrder.getProductInOrderList()){
            if (productInOrder.getProduct() == null || productInOrder.getProduct().getProduct_id() == null){
                throw new RuntimeException("El id de productInOrder es nulo");
            }

            //verificar que haya stock para cada producto
            Long productId = productInOrder.getProduct().getProduct_id();
            Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en la base de datos con el id: " + productId, "P-404"));
            if(productInOrder.getQuantity() > product.getAvailableQuantity()){
                throw new InsufficientStockException("Stock Insuficiente para el product: " + product.getProductName()+ "\n" +
                                                     "Stock Disponible: " + product.getAvailableQuantity() + "\n" +
                                                     "Cantidad Solicitada: " + productInOrder.getQuantity());
            }

            productServ.reduceStock(productId, productInOrder.getQuantity());
            productInOrder.setProduct(product);
            productInOrder.setOrder(newOrder);
        }
        double total = 0;
        for (ProductInOrder productInOrder: newOrder.getProductInOrderList()){
            total += productInOrder.getProduct().getPrice() * productInOrder.getQuantity();
        }
        newOrder.setTotal(total);
        orderRepo.save(newOrder);
    }

    @Override
    public void deleteOrder(Long order_id) {
        if(!orderRepo.existsById(order_id)){
            throw new ResourceNotFoundException("Orden no encontrada con el id: " + order_id, "P-404");
        }
        orderRepo.deleteById(order_id);

    }

    @Override
    public Order readOrder(Long order_id) {
        if(!orderRepo.existsById(order_id)){
            throw new ResourceNotFoundException("Orden no encontrada con el id: " + order_id, "P-404");
        }
        return orderRepo.findById(order_id).orElseThrow(null);
    }

    @Override
    public void updateOrder(Long order_id, Order updatedOrder) {
        if(!orderRepo.existsById(order_id)){
            throw new ResourceNotFoundException("Orden no encontrada con el id: " + order_id, "P-404");
        }
        updatedOrder.setOrder_id(order_id);
        this.newOrder(updatedOrder);
    }

    @Override
    public List<Order> readAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> allOrdersInADay(LocalDate dateOfOrder) {
        return  orderRepo.findOrdersByDate(dateOfOrder);

    }
}
