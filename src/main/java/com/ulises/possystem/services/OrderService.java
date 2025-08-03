package com.ulises.possystem.services;

import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll();
    OrderDTO findById(Long id);
    OrderDTO save(OrderCreateDTO orderDto);
    OrderDTO update(Long id, OrderDTO orderDto);
    OrderDTO cancelOrder(Long id);
}
