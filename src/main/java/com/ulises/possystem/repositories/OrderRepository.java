package com.ulises.possystem.repositories;

import com.ulises.possystem.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{
}
