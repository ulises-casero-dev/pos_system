package com.ulises.selfcheckout.repositories;

import com.ulises.selfcheckout.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByActiveTrue();
    List<User> findByActiveFalse();

    Optional<User> findByMemberIdentification(String identification);

    boolean existsByMemberIdentificationAndActiveFalse(String identification);

    boolean existsByEmail(String email);

    boolean existsByMemberIdentification(String identification);

    @Modifying
    @Query("UPDATE User user SET user.active = :active WHERE user.id = :id")
    void UpdateActiveStatus(@Param("id") Long id,@Param("active") boolean active);
}
