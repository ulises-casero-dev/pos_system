package com.ulises.selfcheckout.discount.strategy;

import com.ulises.selfcheckout.entities.User;
import com.ulises.selfcheckout.helper.UserDiscountResult;

import java.math.BigDecimal;

public interface DiscountStrategy {
    UserDiscountResult applyDiscount(User user, BigDecimal totalPrice);
}
