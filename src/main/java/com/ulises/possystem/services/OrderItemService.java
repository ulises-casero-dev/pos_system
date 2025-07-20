package com.ulises.possystem.services;

import com.ulises.possystem.dto.OrderItemDTO;
import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    OrderItemDTO findById(Long id);
    OrderItemDTO save(OrderItemDTO orderItemDto);
    OrderItemDTO update(Long id, OrderItemDTO orderItemDto);
}
