package com.ulises.possystem.dto.category;

import lombok.*;

@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private boolean active;

    public CategoryDTO() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive(){ return  this.active; }
    public void setActive(boolean active) { this.active = active; }
}
