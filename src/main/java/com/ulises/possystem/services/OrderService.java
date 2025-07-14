package com.ulises.possystem.services;

import com.ulises.possystem.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    Order update(Long id, Order order);
}
