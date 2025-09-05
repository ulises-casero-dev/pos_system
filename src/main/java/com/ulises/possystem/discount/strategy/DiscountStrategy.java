package com.ulises.possystem.discount.strategy;

import com.ulises.possystem.entities.User;
import com.ulises.possystem.helper.UserDiscountResult;

import java.math.BigDecimal;

public interface DiscountStrategy {
    UserDiscountResult applyDiscount(User user, BigDecimal totalPrice);
}
