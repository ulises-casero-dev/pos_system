package com.ulises.selfcheckout.dto.orderItem;

import jakarta.validation.constraints.Min;

public class OrdetItemUpdateDTO {

    @Min(value = 1, message = "The quantity must be at least 1")
    private Integer quantity;

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getQuantity() { return this.quantity; }
}
