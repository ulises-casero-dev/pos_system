package com.ulises.possystem.dto.product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductUpdateDTO {
    private String name;

    @Min(value = 1, message = "Price value must be at least 1")
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
