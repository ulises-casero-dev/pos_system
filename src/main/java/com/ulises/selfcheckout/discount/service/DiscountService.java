package com.ulises.selfcheckout.discount.service;

import com.ulises.selfcheckout.dto.discount.DiscountCreateDTO;
import com.ulises.selfcheckout.dto.discount.DiscountDTO;
import com.ulises.selfcheckout.dto.discount.DiscountUpdateDTO;
import com.ulises.selfcheckout.enums.DiscountType;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> findAll();
    List<DiscountDTO> findByDiscountType(DiscountType discountType);
    List<DiscountDTO> findByDicountStatus(boolean status);
    DiscountDTO findById(Long id);
    DiscountDTO save(DiscountCreateDTO discountCreateDto);
    DiscountDTO patchDiscount(Long id, DiscountUpdateDTO discountUpdateDto);
    void deactivateDiscount(Long id);
    void activateDiscount(Long id);
}
