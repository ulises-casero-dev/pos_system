package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.DiscountType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountDTO {
    private Long id;
    private String description;
    private Double amount;
    private Double limitAmount;
    private DiscountType discountType;
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

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType Type) {
        discountType = Type;
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

    public Double applyDiscount(Double amount) {
        Double totalWithDiscount = amount - ((this.amount * amount) / 100);
        return totalWithDiscount;
    }
}
