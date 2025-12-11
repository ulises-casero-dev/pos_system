package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.UserType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class DiscountUpdateDTO {
    private String description;

    @Min(value = 1, message = "Amount must be at least 1")
    @Max(value = 99, message = "Amount must be less than 100")
    private Double amount;

    @Min(value = 1, message = "Limit amount must be at least 1")
    private Double limitAmount;
    private Long categoryId;
    private Long productId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String desctiprion) {
        this.description = desctiprion;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
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
