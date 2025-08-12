package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.Discount;
import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("Select d FROM Discount d WHERE d.active = true and d.discountType = :type")
    List<Discount>  findByDiscountType(@Param("type") DiscountType type);

}
