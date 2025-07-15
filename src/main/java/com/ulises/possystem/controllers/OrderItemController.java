package com.ulises.possystem.controllers;

import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.services.OrderItemServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {
    @Autowired
    private OrderItemServiceManager serviceManager;

    @GetMapping()
    @Transactional(readOnly = true)
    public List<OrderItem> getOrderITem(){
        return  this.serviceManager.findAll();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public OrderItem getOrderItemById(@PathVariable Long id){
        return this.serviceManager.findById(id);
    }

    @PostMapping()
    @Transactional
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem){
        return this.serviceManager.save(orderItem);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateOrderItem(@PathVariable Long id,
                                             @RequestBody OrderItem orderItem){
        Optional<OrderItem> orderItemData = Optional.of(this.serviceManager.findById(id));

        if(orderItemData.isPresent()){
            OrderItem orderItemToUpdate = orderItemData.get();
            orderItemToUpdate.setQuantity(orderItem.getQuantity());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.serviceManager.update(id,orderItemToUpdate));
        }
        return ResponseEntity.notFound().build();
    }
}
