package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class DiscountCreateDTO {
    private String description;
    private Double amount;
    private boolean isGeneral;
    private Double limitAmount;
    private UserType aplicativeUserType;
    private Long categoryId;
    private Long productId;



    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setAplicativeUserType(UserType aplicativeUserType) {
        this.aplicativeUserType = aplicativeUserType;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean getIsGeneral() {
        return isGeneral;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public UserType getAplicativeUserType() {
        return aplicativeUserType;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }
}
