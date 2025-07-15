package com.ulises.possystem.controllers;

import com.ulises.possystem.entities.Order;
import com.ulises.possystem.services.OrderServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderServiceManager serviceManager;

    @GetMapping()
    @Transactional(readOnly = true)
    public List<Order> getAllOrders(){
        return  this.serviceManager.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Order getOrderById(@PathVariable Long id){
        return this.serviceManager.findById(id);
    }

    @PostMapping()
    @Transactional
    public Order saveOrder(@RequestBody Order order){ return this.serviceManager.save(order); }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateOrder(@PathVariable Long id,
                                   @RequestBody Order order){
        Optional<Order> orderData = Optional.of(this.serviceManager.findById(id));

        if(orderData.isPresent()){
            Order orderToUpdate = orderData.get();
            orderToUpdate.setState(order.getState());
            orderToUpdate.setOrderItems(order.getOrderItems());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.serviceManager.update(id,orderToUpdate));
        }
        return ResponseEntity.notFound().build();
    }
}
