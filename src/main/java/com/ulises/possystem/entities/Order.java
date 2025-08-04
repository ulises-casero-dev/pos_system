package com.ulises.possystem.entities;

import com.ulises.possystem.enums.OrderState;
import com.ulises.possystem.enums.UserType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private Double totalPrice;

    @Column
    private Double totalDiscount;

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
        this.totalPrice = 0.0;
    }
    
    @PrePersist
    @PreUpdate
    public void calculateTotalPrice(){
        if (this.orderItems != null) {
            Double totalPrice = 0.0;
            Double totalDiscount = 0.0;
            for (OrderItem item : this.orderItems) {
                totalPrice += item.getSubTotal();
            }

            totalDiscount = totalPrice;
            if (this.user.getUserType() == UserType.CUSTOMER) {
                totalPrice *= 0.90;
                totalDiscount -= totalPrice;
            } else if (this.user.getUserType() == UserType.EMPLOYEE) {
                totalPrice *= 0.85;
                totalDiscount -= totalPrice;;
            }

            totalPrice = BigDecimal.valueOf(totalPrice)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            totalDiscount = BigDecimal.valueOf(totalDiscount)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();


            this.totalPrice = totalPrice;
            this.totalDiscount = totalDiscount;

        } else {
            this.totalPrice = 0.0;
        }
        
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
