package com.ulises.possystem.dto.order;

import java.time.LocalDateTime;

import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.enums.OrderState;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private Double totalPrice;
    private Double totalDiscount;
    private OrderState state;
    private Long userId;
    private List<OrderItemDTO> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
