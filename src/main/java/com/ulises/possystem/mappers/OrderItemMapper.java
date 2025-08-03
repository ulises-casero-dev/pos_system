package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Order;
import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrderItemDTO toDto(OrderItem orderItem) {
        return this.modelMapper.map(orderItem, OrderItemDTO.class);
    }

    public OrderItem toEntity(OrderCreateDTO orderItemCreateDto) {
        OrderItem orderItem = modelMapper.map(orderItemCreateDto, OrderItem.class);

        return  orderItem;
    }public OrderItem toEntity(OrderItemCreateDTO orderItemDto) {
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);

        Product product = new Product();
        product.setId(orderItemDto.getProductId());
        orderItem.setProduct(product);

        Order order = new Order();
        order.setId(orderItemDto.getOrderId());
        orderItem.setOrder(order);

        return orderItem;
    }

    public OrderItem toEntity(OrderItemDTO orderItemDto) {
        return this.modelMapper.map(orderItemDto, OrderItem.class);
    }
}
