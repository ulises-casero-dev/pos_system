package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();
    List<User> findByActiveFalse();

    @Modifying
    @Query("UPDATE User user SET user.active = :active WHERE user.id = :id")
    void UpdateActiveStatus(@Param("id") Long id,@Param("active") boolean active);
}
