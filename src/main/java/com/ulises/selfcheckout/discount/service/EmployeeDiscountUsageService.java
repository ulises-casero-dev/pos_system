package com.ulises.selfcheckout.discount.service;

import com.ulises.selfcheckout.dto.EmployeeDiscountUsageDTO;

import java.util.List;

public interface EmployeeDiscountUsageService {
    List<EmployeeDiscountUsageDTO> findAll();
    List<EmployeeDiscountUsageDTO> findByActiveTrue();
    EmployeeDiscountUsageDTO findById(Long id);
    EmployeeDiscountUsageDTO save(Long id);
    EmployeeDiscountUsageDTO update(Long id, EmployeeDiscountUsageDTO dto);
    EmployeeDiscountUsageDTO deactivate(Long id);
}
