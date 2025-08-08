package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.entities.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscounMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DiscountDTO toDto(Discount discount) { return this.modelMapper.map(discount, DiscountDTO.class); }

    public Discount toEntitry(DiscountDTO discountDto) { return this.modelMapper.map(discountDto, Discount.class); }
}
