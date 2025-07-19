package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.OrderItemDTO;
import com.ulises.possystem.entities.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrderItemDTO toDto(OrderItem orderItem) {
        return this.modelMapper.map(orderItem, OrderItemDTO.class);
    }

    public OrderItem toEntity(OrderItemDTO orderItemDto) {
        return this.modelMapper.map(orderItemDto, OrderItem.class);
    }
}
