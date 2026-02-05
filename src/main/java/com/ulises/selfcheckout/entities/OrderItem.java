package com.ulises.selfcheckout.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "The quantity most be greater than 1")
    private Integer quantity;
    @Column
    private BigDecimal unitPrice;
    @Column
    private BigDecimal subTotal;
    @NotNull
    @ColumnDefault("false")
    private boolean cancelated;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public  OrderItem() {}

    public OrderItem(Long id, Integer quantity, BigDecimal unitPrice, Order order, Product product){
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
        this.product = product;
        this.subTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @PrePersist
    @PreUpdate
    public void calculateSubTotal(){
        if(unitPrice != null && quantity != null){
            this.subTotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity))
                    .setScale(2, RoundingMode.HALF_EVEN);
        } else {
            this.subTotal = BigDecimal.valueOf(0.00);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
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
