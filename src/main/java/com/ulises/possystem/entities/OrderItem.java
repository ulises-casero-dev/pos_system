package com.ulises.possystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "The quantity mos be greater than 1")
    private Integer quantity;
    @Column
    private Double unitPrice;
    @Column
    private Double subTotal;
    @Column
    private boolean cancelated;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public  OrderItem() {}

    public OrderItem(Long id, Integer quantity, Double unitPrice, Order order, Product product){
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
        this.product = product;
    }

    @PrePersist
    @PreUpdate
    public void calculateSubTotal(){
        if(unitPrice != null && quantity != null){
            this.subTotal = this.unitPrice * this.quantity;
        } else {
            this.subTotal = 0.0;
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean getCancelated() { return this.cancelated; }

    public void setCancelated(boolean cancelated) { this.cancelated = cancelated; }
}
