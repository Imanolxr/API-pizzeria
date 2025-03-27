package com.pizzeriadonremo.Api_pizzeria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Size(min = 2, max = 60)

    @NotBlank(message = "el campo nombre de producto no puede estar vacio")
    private String productName;

    @PositiveOrZero(message = "el precio debe ser mayor o igual a cero" )
    @NotNull(message = "el campo precio no puede estar vacio")
    private Double price;

    @PositiveOrZero(message = "la cantidad disponible debe ser mayor o igual a cero" )
    @NotNull(message = "el campo cantidad disponible no puede estar vacio")
    private int availableQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    private List<ProductInOrder> orderProductList;

}