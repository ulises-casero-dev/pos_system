package com.ulises.possystem.dto.product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductUpdateDTO {
    private String name;
    private Double price;
    private Long categoryId;

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Long getCategoryId() { return this.categoryId; }
}
