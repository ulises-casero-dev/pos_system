package com.ulises.possystem.services;

import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.dto.orderItem.OrdetItemUpdateDTO;
import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.OrderItemRepository;
import com.ulises.possystem.repositories.OrderRepository;
import com.ulises.possystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceManager implements OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderItemDTO> findAll() {
        List<OrderItem> orderItems = this.repository.findAll();
        return orderItems.stream()
                .map(orderItem -> this.modelMapper.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDTO> findByOrderId(Long id) {
        List<OrderItem> allOrderItems = this.repository.findAll();

        List<OrderItem> filteredItems = allOrderItems.stream()
                .filter(item -> !item.getCancelated())
                .collect(Collectors.toList());

        return filteredItems.stream()
                .map(orderItem -> this.modelMapper.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO findById(Long id) {
        OrderItem orderItem = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not Found."));
        return this.modelMapper.map(orderItem, OrderItemDTO.class);
    }

    @Override
    public OrderItemDTO update(Long id, OrdetItemUpdateDTO orderItemDto) {
        OrderItem orderItemEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not Found."));

        orderItemEntity.setQuantity(orderItemDto.getQuantity());

        OrderItem savedOrderItem = this.repository.save(orderItemEntity);
        return this.modelMapper.map(savedOrderItem, OrderItemDTO.class);
    }

    @Override
    public OrderItemDTO cancelOrderItem(Long id) {
        OrderItem orderItemEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found."));

        orderItemEntity.setCancelated(true);

        OrderItem orderItemUpdated = this.repository.save(orderItemEntity);

        return this.modelMapper.map(orderItemUpdated, OrderItemDTO.class);
    }

    @Override
    public OrderItemDTO restoreOrderItem(Long id) {
        OrderItem orderItemEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found."));

        orderItemEntity.setCancelated(false);

        OrderItem orderItemUpdated = this.repository.save(orderItemEntity);

        return this.modelMapper.map(orderItemUpdated, OrderItemDTO.class);
    }
}
