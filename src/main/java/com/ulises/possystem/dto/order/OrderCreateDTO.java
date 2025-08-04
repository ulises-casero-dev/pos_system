package com.ulises.possystem.dto.order;

import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderCreateDTO {
    private Long userId;
    private List<OrderItemCreateDTO> orderItems;

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemCreateDTO> getOrderItems() {
        return orderItems;
    }
}
