package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountDTO {
    private Long id;
    private String description;
    private Double amount;
    private boolean isGeneral;
    private Double limitAmount;
    private UserType aplicativeUserType;
    private Long categoryId;
    private Long productId;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public UserType getAplicativeUserType() {
        return aplicativeUserType;
    }

    public void setAplicativeUserType(UserType aplicativeUserType) {
        this.aplicativeUserType = aplicativeUserType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
