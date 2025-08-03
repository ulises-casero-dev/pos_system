package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.order.OrderDTO;
import com.ulises.possystem.services.OrderServiceManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return  ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> saveOrder(@Valid @RequestBody OrderCreateDTO order){
        OrderDTO orderDto = this.serviceManager.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id,
                                                @Valid @RequestBody OrderDTO order){
        OrderDTO orderDto = this.serviceManager.update(id,order);
        return ResponseEntity.ok(orderDto);
    }
}
