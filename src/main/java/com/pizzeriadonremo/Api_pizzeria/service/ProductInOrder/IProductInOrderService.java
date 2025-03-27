package com.pizzeriadonremo.Api_pizzeria.service.ProductInOrder;

import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.ProductInOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductInOrderService {

    //get products in order details
    public List<ProductInOrderDTO> getProductInOrderDetails();


    public ProductInOrder findProductInOrder(Long productInOrder_id);

    public ProductInOrderDTO readProductInOrder(Long productInOrder_id);

    public void saveProductInOrder(ProductInOrder productInOrder);
    public void deleteProductInOrder(Long productInOrder_id);

}
