package com.ulises.selfcheckout.services;

import com.ulises.selfcheckout.dto.order.OrderCreateDTO;
import com.ulises.selfcheckout.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll();
    OrderDTO findById(Long id);
    OrderDTO save(OrderCreateDTO orderCreateDto);
    OrderDTO update(Long id, OrderDTO orderDto);
    OrderDTO cancelOrder(Long id);
}
