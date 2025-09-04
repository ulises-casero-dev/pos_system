package com.ulises.possystem.repositories;

import com.ulises.possystem.dto.EmployeeDiscountUsageDTO;
import com.ulises.possystem.entities.EmployeeDiscountUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDiscountUsageRepository extends JpaRepository<EmployeeDiscountUsage, Long> {
    @Modifying
    @Query("UPDATE EmployeeDiscountUsage e SET e.active = false WHERE e.id = :id")
    EmployeeDiscountUsage deactivate(Long id);
}
