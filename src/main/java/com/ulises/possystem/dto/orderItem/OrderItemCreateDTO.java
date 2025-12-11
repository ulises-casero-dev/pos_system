package com.ulises.possystem.dto.orderItem;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemCreateDTO {

    @NotNull(message = "Quantity can't be null")
    @Min(value = 1, message = "The quantity must be at last 1")
    private Integer quantity;

    @NotNull(message = "productId can't be null")
    private Long productId;

    @NotNull(message = "OrderId can't be null")
    private Long orderId;

    public Integer getQuantity() { return this.quantity; }

    public Long getProductId() { return this.productId; }

    public Long getOrderId() { return this.orderId; }
}
