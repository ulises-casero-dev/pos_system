package com.ulises.possystem.entities;

import com.ulises.possystem.enums.UserType;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column
    @ColumnDefault("false")
    private boolean active;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean idGeneral;
    @Column(nullable = false)
    private Double limit;
    @Enumerated(EnumType.STRING)
    @Column(name = "aplicative_user_type")
    private UserType aplicativeUserType;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Discount() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(boolean idGeneral) {
        this.idGeneral = idGeneral;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public UserType getAplicativeUserType() {
        return aplicativeUserType;
    }

    public void setAplicativeUserType(UserType aplicativeUserType) {
        this.aplicativeUserType = aplicativeUserType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
