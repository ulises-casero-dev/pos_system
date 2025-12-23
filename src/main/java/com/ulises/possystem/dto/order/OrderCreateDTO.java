package com.ulises.possystem.dto.order;

import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderCreateDTO {

    @NotNull(message = "User id can't be null")
    private Long userId;

    @NotNull(message = "The list of items can't be null")
    private List<OrderItemCreateDTO> orderItems;

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemCreateDTO> getOrderItems() {
        return orderItems;
    }
}
