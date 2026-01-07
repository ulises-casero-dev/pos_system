package com.ulises.possystem.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductCreateDTO {

    @NotBlank(message = "The name of the product can't be empty")
    @Size(max = 100, message = "The product name must no exceed 100 characters")
    private String name;

    @NotNull(message = "The price can't be null")
    @Min(value = 1, message = "The price must be greater than 1")
    private BigDecimal price;

    @NotNull(message = "The categorie of the product can't be null")
    private Long categoryId;

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Long getCategoryId() { return this.categoryId; }
}
