package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.OrderItemDTO;
import com.ulises.possystem.services.OrderItemServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {
    @Autowired
    private OrderItemServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<OrderItemDTO>> getOrderITem(){
        return  ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<OrderItemDTO> saveOrderItem(@Valid  @RequestBody OrderItemDTO orderItem){
        return ResponseEntity.ok(this.serviceManager.save(orderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id,
                                             @RequestBody OrderItemDTO orderItem){
        OrderItemDTO orderItemDto = this.serviceManager.update(id,orderItem);
        return ResponseEntity.ok(orderItemDto);
    }
}
