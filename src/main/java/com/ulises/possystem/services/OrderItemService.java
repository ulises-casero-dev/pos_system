package com.ulises.possystem.services;

import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.dto.orderItem.OrdetItemUpdateDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    List<OrderItemDTO> findByOrderId(Long id);
    OrderItemDTO findById(Long id);
    OrderItemDTO save(OrderItemCreateDTO itemDto);
    OrderItemDTO update(Long id, OrdetItemUpdateDTO orderItemDto);
    OrderItemDTO cancelOrderItem(Long id);
    OrderItemDTO restoreOrderItem(Long id);
}
