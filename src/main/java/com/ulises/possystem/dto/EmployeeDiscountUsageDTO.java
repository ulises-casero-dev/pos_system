package com.ulises.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDiscountUsageDTO {
    private Long id;
    private Long employeeId;
    private BigDecimal acumulatedAmount;
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long id) {
        this.employeeId = id;
    }

    public BigDecimal getAcumulatedAmount() {
        return acumulatedAmount;
    }

    public void setAcumulatedAmount(BigDecimal acumulatedAmount) {
        this.acumulatedAmount = acumulatedAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
