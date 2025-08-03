package com.ulises.possystem.dto.orderItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemCreateDTO {
    private Integer quantity;
    private Long productId;
    private Long orderId;

    public Integer getQuantity() { return this.quantity; }

    public Long getProductId() { return this.productId; }

    public Long getOrderId() { return this.orderId; }
}
