package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountCreateDTO {
    private String desctiprion;
    private Double amount;
    private boolean isGeneral;
    private Double limitAmount;
    private UserType aplicativeUserType;
    private Long categoryId;
    private Long productId;

    public String getDesctiprion() {
        return desctiprion;
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
