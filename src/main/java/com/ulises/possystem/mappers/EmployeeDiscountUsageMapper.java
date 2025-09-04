package com.ulises.possystem.mappers;

import com.ulises.possystem.dto.EmployeeDiscountUsageDTO;
import com.ulises.possystem.entities.EmployeeDiscountUsage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDiscountUsageMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDiscountUsageDTO toDto(EmployeeDiscountUsage Entity) { return this.modelMapper.map(Entity, EmployeeDiscountUsageDTO.class); }

    public EmployeeDiscountUsage toEntity(EmployeeDiscountUsageDTO dto) { return this.modelMapper.map(dto, EmployeeDiscountUsage.class); }
}
