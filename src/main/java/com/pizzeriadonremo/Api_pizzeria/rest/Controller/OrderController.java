package com.pizzeriadonremo.Api_pizzeria.rest.Controller;

import com.pizzeriadonremo.Api_pizzeria.model.Order;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.OrderDTO;
import com.pizzeriadonremo.Api_pizzeria.rest.mapper.OrderDTOMapper;
import com.pizzeriadonremo.Api_pizzeria.service.Order.IOrderService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    private final IOrderService orderServ;
    private final OrderDTOMapper orderDTOMapper;

    public OrderController(IOrderService orderServ, OrderDTOMapper orderDTOMapper) {
        this.orderServ = orderServ;
        this.orderDTOMapper = orderDTOMapper;
    }

    @PostMapping("/newOrder")
    public ResponseEntity<String> newOrder(@Valid @RequestBody Order newOrder, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validacion de los datos de la orden", HttpStatus.BAD_REQUEST);
        }
        orderServ.newOrder(newOrder);
        return new ResponseEntity<>("Orden Creada", HttpStatus.CREATED);
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long order_id){
        orderServ.deleteOrder(order_id);
        return new ResponseEntity<>("Orden Eliminada", HttpStatus.ACCEPTED);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDTO> readOrder(@PathVariable Long order_id){
        Order order = orderServ.readOrder(order_id);
        OrderDTO orderDTO = orderDTOMapper.toDTO(order);
        return new ResponseEntity<>(orderDTO,HttpStatus.FOUND);
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<String> modifyOrder(@Valid @RequestBody Order updatedOrder, BindingResult result, @PathVariable Long order_id){
        if (result.hasErrors()){
            return new ResponseEntity<>("Error en la validacion de los datos de la orden", HttpStatus.BAD_REQUEST);
        }
        orderServ.updateOrder(order_id, updatedOrder);
        return new ResponseEntity<>("Orden Actualizada", HttpStatus.OK);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderDTO>> readAllOrders(){
        List<Order> allOrders = orderServ.readAllOrders();
        List<OrderDTO> allOrdersDTO = new ArrayList<>();
        for (Order orderInList: allOrders){
            OrderDTO orderDTO = OrderDTOMapper.INSTANCE.toDTO(orderInList);
            allOrdersDTO.add(orderDTO);
        }
        return new ResponseEntity<>(allOrdersDTO, HttpStatus.OK);
    }

    @GetMapping("/ordersByDate")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(@RequestParam(value = "date", required = false) String dateString){
        LocalDate date = (dateString != null) ? LocalDate.parse(dateString) : LocalDate.now();
        List<Order> ordersByDate = orderServ.allOrdersInADay(date);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order orderList: ordersByDate){
            OrderDTO orderDTO = orderDTOMapper.toDTO(orderList);
            orderDTOList.add(orderDTO);
        }
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }
}
