package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.UserType;

public class DiscountUpdateDTO {
    private String desctiprion;
    private Double amount;
    private Double limitAmount;
    private Long categoryId;
    private Long productId;

    public String getDesctiprion() {
        return desctiprion;
    }

    public Double getAmount() {
        return amount;
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
