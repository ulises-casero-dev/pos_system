package com.ulises.possystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_discount_usages")
public class EmployeeDiscountUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="employee_id")
    private User employeeM;
    @Column(nullable = false)
    private Double acumulatedAmount;

    public EmployeeDiscountUsage() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployeeM() {
        return employeeM;
    }

    public void setEmployeeM(User employeeM) {
        this.employeeM = employeeM;
    }

    public Double getAcumulatedAmount() {
        return acumulatedAmount;
    }

    public void setAcumulatedAmount(Double acumulatedAmount) {
        this.acumulatedAmount = acumulatedAmount;
    }
}
