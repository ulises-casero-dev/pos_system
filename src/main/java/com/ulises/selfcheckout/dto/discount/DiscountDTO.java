package com.ulises.selfcheckout.dto.discount;

import com.ulises.selfcheckout.enums.DiscountType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class DiscountDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private BigDecimal limitAmount;
    private DiscountType discountType;
    private boolean active;
    private Long categoryId;
    private Long productId;

    public DiscountDTO(){}

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType Type) {
        discountType = Type;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public BigDecimal applyDiscount(BigDecimal amount) {
        BigDecimal discountAmount = this.amount.multiply(amount)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);

        BigDecimal totalWithDiscount = amount.subtract(discountAmount);

        return totalWithDiscount;
    }
}
