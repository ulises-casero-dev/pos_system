package com.ulises.possystem.helper;

import java.math.BigDecimal;

public class UserDiscountResult {
    private BigDecimal totalAfterUserDiscount;
    private BigDecimal userDiscountAmount;

    public UserDiscountResult(BigDecimal userDiscountAmount, BigDecimal totalAfterUserDiscount){
        this.userDiscountAmount = userDiscountAmount;
        this.totalAfterUserDiscount = totalAfterUserDiscount;
    }

    public BigDecimal getTotalAfterUserDiscount() {
        return totalAfterUserDiscount;
    }

    public BigDecimal getUserDiscountAmount() {
        return userDiscountAmount;
    }
}
