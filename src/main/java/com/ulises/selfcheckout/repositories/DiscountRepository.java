package com.ulises.selfcheckout.repositories;

import com.ulises.selfcheckout.entities.Discount;
import com.ulises.selfcheckout.enums.DiscountType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.active = true and d.discountType = :type")
    List<Discount> findByDiscountType(@Param("type") DiscountType type);

    @Query("SELECT d FROM Discount d WHERE d.active = :status")
    List<Discount> findByDicountStatus(@Param("status") boolean status);

    @Transactional
    @Modifying
    @Query("UPDATE Discount d SET d.active = :status WHERE d.id = :id")
    void updateActivateStatus(@Param("status") boolean status, @Param("id") Long id);

}
