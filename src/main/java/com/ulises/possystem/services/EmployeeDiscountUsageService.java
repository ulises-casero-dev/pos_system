package com.ulises.possystem.services;

import com.ulises.possystem.dto.EmployeeDiscountUsageDTO;

import java.util.List;

public interface EmployeeDiscountUsageService {
    List<EmployeeDiscountUsageDTO> findAll();
    EmployeeDiscountUsageDTO findById(Long id);
    EmployeeDiscountUsageDTO save(Long id);
    EmployeeDiscountUsageDTO update(Long id, EmployeeDiscountUsageDTO dto);
    EmployeeDiscountUsageDTO deactivate(Long id);
}
