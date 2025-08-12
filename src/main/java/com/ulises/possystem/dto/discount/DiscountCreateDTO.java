package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class DiscountCreateDTO {
    private String description;
    private Double amount;
    private DiscountType discountType;
    private Double limitAmount;
    private Long categoryId;
    private Long productId;



    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDiscountType(DiscountType Type) {
        discountType = Type;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
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

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }
}
