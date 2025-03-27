package com.pizzeriadonremo.Api_pizzeria.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInOrderDTO {
    private String productName;
    private int quantity;
    private Double price;
    private Double subtotal;

}
