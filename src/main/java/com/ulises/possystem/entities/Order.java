package com.ulises.possystem.entities;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private Double totalPrice;

    @Column
    private Double totalDiscount;

    @Column
    private String state;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    
    public Order(){}
    
    public Order(Long id, User user){
        this.id = id;
        this.user = user;
    }
    
    @PrePersist
    @PreUpdate
    public void calculateTotalPrice(){
        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                this.totalPrice += item.getSubTotal();
            }    
        } else {
            this.totalPrice = 0.0;
        }
        
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
