package com.ulises.possystem.services;

import com.ulises.possystem.entities.OrderItem;
import java.util.List;
public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem findById(Long id);
    OrderItem save(OrderItem orderItem);
    OrderItem update(Long id, OrderItem orderItem);
}
