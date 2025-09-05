package com.ulises.possystem.entities;

import com.ulises.possystem.enums.OrderState;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal totalDiscount;

    @Column
    @Nullable
    private boolean cancelated = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public Order(){}
    
    public Order(Long id, User user){
        this.id = id;
        this.user = user;
        this.totalPrice = BigDecimal.valueOf(0.00);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public boolean getCancelated() { return this.cancelated; }

    public void setCancelated(boolean cancelated) { this.cancelated = cancelated; }
}
