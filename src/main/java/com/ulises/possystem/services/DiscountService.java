package com.ulises.possystem.services;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> findAll();
    DiscountDTO findById(Long id);
    DiscountDTO save(DiscountCreateDTO discountCreateDto);
    DiscountDTO update(DiscountUpdateDTO discountUpdateDto);
    DiscountDTO deactivateDiscount(Long id);
    DiscountDTO activateDiscount(Long id);
}
