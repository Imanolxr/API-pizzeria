package com.pizzeriadonremo.Api_pizzeria.rest.mapper;


import com.pizzeriadonremo.Api_pizzeria.model.Order;
import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.OrderDTO;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.ProductInOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDTOMapper {
    OrderDTOMapper INSTANCE = Mappers.getMapper(OrderDTOMapper.class);

    @Mapping(source = "order_id", target = "orderId")
    @Mapping(source = "dateOfOrder", target = "orderDate")
    @Mapping(source = "productInOrderList", target = "productInOrderDTOList")
    OrderDTO toDTO(Order order);

    // Mapeo de la lista de ProductInOrder a ProductInOrderDTO
    default List<ProductInOrderDTO> mapProductInOrderList(List<ProductInOrder> productInOrderList) {
        if (productInOrderList == null) {
            return null;
        }
        return productInOrderList.stream()
                .map(ProductInOrderDTOMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
