package com.pizzeriadonremo.Api_pizzeria.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Size(min = 2, max = 60)
    @NotBlank(message = "el campo nombre de categoria no puede estar vacio")
    private String categoryName;
    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<Product> productsInCategory;

    public Category(Long category_id, String categoryName) {
        this.category_id = category_id;
        this.categoryName = categoryName;
    }
}
