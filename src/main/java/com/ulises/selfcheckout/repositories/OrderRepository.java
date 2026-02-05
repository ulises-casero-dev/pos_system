package com.ulises.selfcheckout.repositories;

import com.ulises.selfcheckout.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("""
            SELECT DISTINCT o
            FROM Order o
            JOIN FETCH o.user
            LEFT JOIN FETCH o.orderItems oi
            """)
    List<Order> findAllWithDetails();
}
