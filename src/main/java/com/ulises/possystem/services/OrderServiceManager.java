package com.ulises.possystem.services;

import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.order.OrderDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.entities.Order;
import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.OrderRepository;
import com.ulises.possystem.repositories.ProductRepository;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceManager implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
                orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        return this.modelMapper.map(orderEntity, OrderDTO.class);
    }
    @Override
    public OrderDTO save(OrderCreateDTO orderCreateDto) {
        Order orderEntity = new Order();

        User user = this.userRepository.findById(orderCreateDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        List<OrderItemDTO> orderItems = orderCreateDto.getOrderItems().stream()
                .map(itemDto -> {
                    Product product = this.productRepository.findById(itemDto.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(orderEntity);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setUnitPrice(itemDto.getUnitPrice());
                    orderItem.setSubTotal(itemDto.getUnitPrice() * itemDto.getQuantity());

                    return itemDto;
                }).collect(Collectors.toList());

        Double totalPrice = orderItems.stream()
                .mapToDouble(OrderItemDTO::getSubTotal)
                .sum();

        orderEntity.setUser(user);
        orderEntity.setDate(LocalDateTime.now());
        orderEntity.setTotalPrice(totalPrice);
        //orderEntity.setOrderItems(orderItems);


        //Order orderEntity = this.modelMapper.map(orderDto, Order.class);
        Order savedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public  OrderDTO update(Long id, OrderDTO orderDto) {
        Order orderEntity = this.repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        orderEntity.setState(orderDto.getState());
        orderEntity.setTotalDiscount(orderDto.getTotalDiscount());

        Order updatedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(updatedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO cancelOrder(Long id)  {
        Order orderEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        orderEntity.setCancelated(true);

        Order orderCanceled = this.repository.save(orderEntity);

        return this.modelMapper.map(orderCanceled, OrderDTO.class);
    }
}

