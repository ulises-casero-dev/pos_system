package com.ulises.selfcheckout.discount.strategy;

import com.ulises.selfcheckout.discount.service.DiscountServiceManager;
import com.ulises.selfcheckout.dto.discount.DiscountDTO;
import com.ulises.selfcheckout.entities.User;
import com.ulises.selfcheckout.enums.DiscountType;
import com.ulises.selfcheckout.helper.UserDiscountResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDiscountStrategy implements DiscountStrategy {

    private final DiscountServiceManager discountServiceManager;

    @Override
    public UserDiscountResult applyDiscount(User user, BigDecimal totalPrice) {

        List<DiscountDTO> discounts = this.discountServiceManager.findByDiscountType(DiscountType.CUSTOMER);


        if (discounts == null || discounts.isEmpty()) {
            return new UserDiscountResult(BigDecimal.ZERO, totalPrice);
        }

        DiscountDTO discountDto = discounts.get(0);
        if( discountDto.getAmount() == null || discountDto.getLimitAmount() == null) {
            throw new IllegalStateException("Discount configuration is incomplete.");
        }

        BigDecimal totalWithDiscount = discountDto.applyDiscount(totalPrice);
        BigDecimal totalDiscount = totalPrice.subtract(totalWithDiscount);

        return new UserDiscountResult(totalDiscount, totalWithDiscount);
    }
}
