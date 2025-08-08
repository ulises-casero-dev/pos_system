package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.services.DiscountServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    @Autowired
    private DiscountServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<DiscountDTO>> findAll(){
        return ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public  ResponseEntity<DiscountDTO> saveDiscount(@RequestBody DiscountCreateDTO discount){
        DiscountDTO discountDto = this.serviceManager.save(discount);
        return ResponseEntity.status(HttpStatus.CREATED).body(discountDto);
    }
}
