package com.ulises.possystem.services;

import com.ulises.possystem.entities.Order;
import com.ulises.possystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceManager implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(@RequestBody Order order) {
        return this.repository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) this.repository.findAll();
    }

    @Override
    public  Order update(@RequestBody Long id, Order order){
        Order actualOrder = this.repository.findById(id).get();

        actualOrder.setState(order.getState());
        actualOrder.setTotalPrice(order.getTotalPrice());
        actualOrder.setTotalDiscount(order.getTotalDiscount());
        actualOrder.setOrderItems(order.getOrderItems());

        return this.repository.save(actualOrder);
    }
}

