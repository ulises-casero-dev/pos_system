package com.ulises.selfcheckout.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryCreateDTO {
    @NotBlank(message = "The name of the category can't be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
