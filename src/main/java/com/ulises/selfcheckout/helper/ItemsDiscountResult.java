package com.ulises.selfcheckout.helper;

import java.math.BigDecimal;

public class ItemsDiscountResult {
    private BigDecimal totalAfterItemsDiscounts;
    private BigDecimal itemsDiscountAmount;

    public ItemsDiscountResult(BigDecimal itemsDiscountAmount, BigDecimal totalAfterItemsDiscounts){
        this.itemsDiscountAmount = itemsDiscountAmount;
        this.totalAfterItemsDiscounts = totalAfterItemsDiscounts;
    }

    public BigDecimal getTotalAfterItemsDiscounts() {
        return totalAfterItemsDiscounts;
    }

    public BigDecimal getItemsDiscountAmount() {
        return itemsDiscountAmount;
    }
}
