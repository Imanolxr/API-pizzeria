package com.pizzeriadonremo.Api_pizzeria.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Double total;
    private List<ProductInOrderDTO> productInOrderDTOList;
}
