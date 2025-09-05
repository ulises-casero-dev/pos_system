package com.ulises.possystem.dto.product;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductUpdateDTO {
    private String name;
    private BigDecimal price;
    private Long categoryId;

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Long getCategoryId() { return this.categoryId; }
}
