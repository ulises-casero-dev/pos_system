package com.ulises.selfcheckout.services;

import com.ulises.selfcheckout.dto.orderItem.OrderItemDTO;
import com.ulises.selfcheckout.dto.orderItem.OrdetItemUpdateDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    List<OrderItemDTO> findByOrderId(Long id);
    OrderItemDTO findById(Long id);
    OrderItemDTO update(Long id, OrdetItemUpdateDTO orderItemDto);
    OrderItemDTO cancelOrderItem(Long id);
    OrderItemDTO restoreOrderItem(Long id);
}
