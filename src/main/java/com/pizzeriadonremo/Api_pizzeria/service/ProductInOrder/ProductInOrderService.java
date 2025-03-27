package com.pizzeriadonremo.Api_pizzeria.service.ProductInOrder;

import com.pizzeriadonremo.Api_pizzeria.exception.ResourceNotFoundException;
import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import com.pizzeriadonremo.Api_pizzeria.repository.Product.IProductRepository;
import com.pizzeriadonremo.Api_pizzeria.repository.ProductInOrder.IProductInOrderRepository;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.ProductInOrderDTO;
import com.pizzeriadonremo.Api_pizzeria.rest.mapper.ProductInOrderDTOMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInOrderService implements IProductInOrderService{
    private final ProductInOrderDTOMapper productInOrderDTOMapper;
    private final IProductInOrderRepository productInOrderRepo;
    private final IProductRepository productRepo;

    public ProductInOrderService(ProductInOrderDTOMapper productInOrderDTOMapper, IProductInOrderRepository productInOrderRepo, IProductRepository productRepo) {
        this.productInOrderDTOMapper = productInOrderDTOMapper;
        this.productInOrderRepo = productInOrderRepo;
        this.productRepo = productRepo;
    }




    @Override
    public List<ProductInOrderDTO> getProductInOrderDetails() {
        List<ProductInOrderDTO> productOrderListDTO = new ArrayList<>();
        List<ProductInOrder> productInOrderList = productInOrderRepo.findAll();
        for(ProductInOrder productInOrder : productInOrderList){
            ProductInOrderDTO dto = productInOrderDTOMapper.toDTO(productInOrder);
            productOrderListDTO.add(dto);
        }
        return productOrderListDTO;
    }

    @Override
    public ProductInOrder findProductInOrder(Long productInOrder_id) {
        return productInOrderRepo.findById(productInOrder_id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el id: " + productInOrder_id, "P-404"));
    }

    @Override
    public ProductInOrderDTO readProductInOrder(Long productInOrder_id) {
        ProductInOrder productInOrder = productInOrderRepo.findById(productInOrder_id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el id: " + productInOrder_id, "P-404"));
        return productInOrderDTOMapper.toDTO(productInOrder);
    }

    @Override
    @Transactional
    public void saveProductInOrder(ProductInOrder productInOrder) {
        productInOrderRepo.save(productInOrder);

    }

    @Override
    @Transactional
    public void deleteProductInOrder(Long productInOrder_id) {
        productInOrderRepo.deleteById(productInOrder_id);
    }


}
