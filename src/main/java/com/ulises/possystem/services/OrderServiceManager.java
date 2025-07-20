package com.ulises.possystem.services;

import com.ulises.possystem.dto.OrderDTO;
import com.ulises.possystem.entities.Order;
import com.ulises.possystem.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceManager implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = this.repository.findAll();
        return orders.stream()
                .map(order -> this.modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        Order orderEntity = this.repository.findById(id).
                orElseThrow(() -> new RuntimeException("Order not found."));

        return this.modelMapper.map(orderEntity, OrderDTO.class);
    }
    @Override
    public OrderDTO save(OrderDTO orderDto) {
        Order orderEntity = this.modelMapper.map(orderDto, Order.class);
        Order savedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public  OrderDTO update(Long id, OrderDTO orderDto){
        Order orderEntity = this.repository.findById(id).
                orElseThrow(() -> new RuntimeException("Order not found."));

        orderEntity.setState(orderDto.getState());
        orderEntity.setTotalDiscount(orderDto.getTotalDiscount());

        Order updatedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(updatedOrder, OrderDTO.class);
    }
}

