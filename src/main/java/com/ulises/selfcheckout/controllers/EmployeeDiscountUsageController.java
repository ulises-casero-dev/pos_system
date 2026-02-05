package com.ulises.selfcheckout.controllers;

import com.ulises.selfcheckout.dto.EmployeeDiscountUsageDTO;
import com.ulises.selfcheckout.discount.service.EmployeeDiscountUsageServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee_discount_usages")
public class EmployeeDiscountUsageController {
    @Autowired
    private EmployeeDiscountUsageServiceManager serviceManager;

    @GetMapping()
    public ResponseEntity<List<EmployeeDiscountUsageDTO>> findAll() {
        return ResponseEntity.ok(this.serviceManager.findAll());
    }

    @GetMapping("/actives")
    public ResponseEntity<List<EmployeeDiscountUsageDTO>> findByActiveTrue() {
        return ResponseEntity.ok(this.serviceManager.findByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDiscountUsageDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.serviceManager.findById(id));
    }

    @PostMapping
    private ResponseEntity<EmployeeDiscountUsageDTO> saveEmployeeDiscountUsage(@RequestBody EmployeeDiscountUsageDTO dto) {
        System.out.println("Id de usuario: "+ dto.getEmployeeId());
        EmployeeDiscountUsageDTO entityDto = this.serviceManager.save(dto.getEmployeeId());
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<EmployeeDiscountUsageDTO> updateEmployeDiscountUsage(@PathVariable Long id,
                                                                                @RequestBody EmployeeDiscountUsageDTO dto) {
        return ResponseEntity.ok(this.serviceManager.update(id, dto));
    }

    @PutMapping("/deactivate/{id}")
    private ResponseEntity<EmployeeDiscountUsageDTO> deactivateEmployeDiscountUsage(@PathVariable Long id) {
        return ResponseEntity.ok(this.serviceManager.deactivate(id));
    }
}
