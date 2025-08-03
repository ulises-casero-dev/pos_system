package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.order.OrderDTO;
import com.ulises.possystem.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO toDto(Order order){
        return this.modelMapper.map(order, OrderDTO.class);
    }

    public Order toEntity(OrderDTO orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }
}
