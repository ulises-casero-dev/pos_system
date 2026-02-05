package com.ulises.selfcheckout.mappers;

import com.ulises.selfcheckout.dto.EmployeeDiscountUsageDTO;
import com.ulises.selfcheckout.entities.EmployeeDiscountUsage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDiscountUsageMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDiscountUsageDTO toDto(EmployeeDiscountUsage Entity) { return this.modelMapper.map(Entity, EmployeeDiscountUsageDTO.class); }

    public EmployeeDiscountUsage toEntity(EmployeeDiscountUsageDTO dto) { return this.modelMapper.map(dto, EmployeeDiscountUsage.class); }
}
