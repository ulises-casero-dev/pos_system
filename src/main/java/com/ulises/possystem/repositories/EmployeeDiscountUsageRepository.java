package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.EmployeeDiscountUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDiscountUsageRepository extends JpaRepository<EmployeeDiscountUsage, Long> {
}
