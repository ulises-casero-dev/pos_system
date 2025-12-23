package com.ulises.possystem.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryCreateDTO {
    @NotNull(message = "The category name can't be null.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
