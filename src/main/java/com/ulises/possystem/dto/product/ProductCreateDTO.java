package com.ulises.possystem.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class ProductCreateDTO {

    @NotNull(message = "The name of the product can't be null")
    private String name;

    @NotNull(message = "The price of the product can't be null")
    @Min(value = 1, message = "Price value must be at least 1")
    private Double price;

    @NotNull(message = "The categorie of the product can't be null")
    private Long categoryId;

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Long getCategoryId() { return this.categoryId; }
}
