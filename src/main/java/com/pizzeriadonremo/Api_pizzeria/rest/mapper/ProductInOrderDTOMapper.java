package com.pizzeriadonremo.Api_pizzeria.rest.mapper;
import com.pizzeriadonremo.Api_pizzeria.model.ProductInOrder;
import com.pizzeriadonremo.Api_pizzeria.rest.dto.ProductInOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper (componentModel = "spring")
public interface ProductInOrderDTOMapper {
    ProductInOrderDTOMapper INSTANCE = Mappers.getMapper(ProductInOrderDTOMapper.class);


    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "product.price", target = "price")

    ProductInOrderDTO toDTO(ProductInOrder productInOrder);

}
