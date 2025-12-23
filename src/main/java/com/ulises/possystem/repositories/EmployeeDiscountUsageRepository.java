package com.ulises.possystem.repositories;

import com.ulises.possystem.dto.EmployeeDiscountUsageDTO;
import com.ulises.possystem.entities.EmployeeDiscountUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDiscountUsageRepository extends JpaRepository<EmployeeDiscountUsage, Long> {
    List<EmployeeDiscountUsage> findByActiveTrue();
    @Modifying
    @Query("UPDATE EmployeeDiscountUsage e SET e.active = false WHERE e.id = :id")
    EmployeeDiscountUsage deactivate(Long id);

    @Query("SELECT e FROM EmployeeDiscountUsage e WHERE e.employee.id = :id")
    EmployeeDiscountUsage getByEmployeeId(Long id);
}
