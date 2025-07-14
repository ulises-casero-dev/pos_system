package com.ulises.possystem.services;

import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderItemServiceManager implements OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    @Override
    public List<OrderItem> findAll() {
        return (List<OrderItem>) this.repository.findAll();
    }

    @Override
    public OrderItem findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public OrderItem save(@RequestBody OrderItem orderItem) {
        return this.repository.save(orderItem);
    }

    @Override
    public OrderItem update(@RequestBody Long id, OrderItem orderItem) {
        OrderItem orderItemData = this.repository.findById(id).get();

        orderItemData.setQuantity(orderItem.getQuantity());

        return this.repository.save(orderItemData);
    }
}
