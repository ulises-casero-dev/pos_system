package com.ulises.possystem.controllers;

import com.ulises.possystem.dto.ApiMessage;
import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;
import com.ulises.possystem.discount.service.DiscountServiceManager;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DiscountDTO>> findAll() {
        return ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping()
    public ResponseEntity<DiscountDTO> saveDiscount(@Valid @RequestBody DiscountCreateDTO discount) {
        DiscountDTO discountDto = this.serviceManager.save(discount);
        return ResponseEntity.status(HttpStatus.CREATED).body(discountDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiscountDTO> updateDiscount(@PathVariable Long id,
                                                      @Valid @RequestBody DiscountUpdateDTO discountDto) {
        return ResponseEntity.ok(this.serviceManager.patchDiscount(id, discountDto));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiMessage> deactivateDiscount(@PathVariable Long id) {
        this.serviceManager.deactivateDiscount(id);
        return ResponseEntity.ok(new ApiMessage("Discount deactivation **successful**"));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<ApiMessage> activateDiscount(@PathVariable Long id) {
        this.serviceManager.activateDiscount(id);
        return ResponseEntity.ok(new ApiMessage("Discount activation **successful**"));
    }
}
