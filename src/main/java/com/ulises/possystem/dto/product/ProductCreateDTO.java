package com.ulises.possystem.dto.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class ProductCreateDTO {
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
