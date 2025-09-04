package com.ulises.possystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "employee_discount_usages")
public class EmployeeDiscountUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="employee_id")
    private User employee;
    @Column
    @ColumnDefault("0.00")
    private Double acumulatedAmount = 0.00;

    @Column
    @ColumnDefault("true")
    private boolean active = true;

    public EmployeeDiscountUsage() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Double getAcumulatedAmount() {
        return acumulatedAmount;
    }

    public void setAcumulatedAmount(Double acumulatedAmount) {
        this.acumulatedAmount = acumulatedAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
