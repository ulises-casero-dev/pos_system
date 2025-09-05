package com.ulises.possystem.discount.service;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;
import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.enums.UserType;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> findAll();
    List<DiscountDTO> findByDiscountType(DiscountType discountType);
    DiscountDTO findById(Long id);
    DiscountDTO save(DiscountCreateDTO discountCreateDto);
    DiscountDTO update(Long id, DiscountUpdateDTO discountUpdateDto);
    DiscountDTO deactivateDiscount(Long id);
    DiscountDTO activateDiscount(Long id);
}
