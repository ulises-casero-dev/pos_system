package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.dto.orderItem.OrderItemDTO;
import com.ulises.possystem.dto.orderItem.OrdetItemUpdateDTO;
import com.ulises.possystem.services.OrderItemServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {
    @Autowired
    private OrderItemServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<OrderItemDTO>> getOrderITem(){
        return  ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/findByOrder/{id}")
    public ResponseEntity<List<OrderItemDTO>> getOrderITemByOrderId(Long id){
        return  ResponseEntity.ok(this.serviceManager.findByOrderId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<OrderItemDTO> saveOrderItem(@Valid @RequestBody OrderItemCreateDTO orderItem){
        return ResponseEntity.ok(this.serviceManager.save(orderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id,
                                                        @Valid @RequestBody OrdetItemUpdateDTO orderItem){
        OrderItemDTO orderItemDto = this.serviceManager.update(id,orderItem);
        return ResponseEntity.ok(orderItemDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItemDTO> cancelOrderItem(@PathVariable Long id) {
        OrderItemDTO cancelatedOrderItemDTO = this.serviceManager.cancelOrderItem(id);
        return ResponseEntity.ok(cancelatedOrderItemDTO);
    }

    @PutMapping("/restore/{id}")
    public  ResponseEntity<OrderItemDTO> restoreOrderItem(@PathVariable Long id) {
        OrderItemDTO restoreOrderItemDTO = this.serviceManager.restoreOrderItem(id);
        return ResponseEntity.ok(restoreOrderItemDTO);
    }
}
