package com.ulises.possystem.helper;

public class UserDiscountResult {
    private Double totalAfterUserDiscount;
    private Double userDiscountAmount;

    public UserDiscountResult(Double userDiscountAmount, Double totalAfterUserDiscount){
        this.userDiscountAmount = userDiscountAmount;
        this.totalAfterUserDiscount = totalAfterUserDiscount;
    }

    public Double getTotalAfterUserDiscount() {
        return totalAfterUserDiscount;
    }

    public Double getUserDiscountAmount() {
        return userDiscountAmount;
    }
}
