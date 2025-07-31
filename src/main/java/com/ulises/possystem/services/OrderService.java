package com.ulises.possystem.services;

import com.ulises.possystem.dto.OrderDTO;
import com.ulises.possystem.entities.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll();
    OrderDTO findById(Long id);
    OrderDTO save(OrderDTO orderDto);
    OrderDTO update(Long id, OrderDTO orderDto);
    OrderDTO cancelOrder(Long id);
}
