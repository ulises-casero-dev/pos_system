package com.ulises.possystem.helper;

public class ItemsDiscountResult {
    private Double totalAfterItemsDiscounts;
    private Double itemsDiscountAmount;

    public ItemsDiscountResult(Double itemsDiscountAmount, Double totalAfterItemsDiscounts){
        this.itemsDiscountAmount = itemsDiscountAmount;
        this.totalAfterItemsDiscounts = totalAfterItemsDiscounts;
    }

    public Double getTotalAfterItemsDiscounts() {
        return totalAfterItemsDiscounts;
    }

    public Double getItemsDiscountAmount() {
        return itemsDiscountAmount;
    }
}
