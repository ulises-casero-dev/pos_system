package com.ulises.possystem.dto;

import java.time.LocalDateTime;
import com.ulises.possystem.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Double totalDiscount;
    private OrderState state;
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
