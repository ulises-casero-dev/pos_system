package com.ulises.possystem.discount.service;

import com.ulises.possystem.dto.EmployeeDiscountUsageDTO;
import com.ulises.possystem.entities.EmployeeDiscountUsage;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.enums.UserType;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.EmployeeDiscountUsageRepository;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDiscountUsageServiceManager implements EmployeeDiscountUsageService {
    @Autowired
    private EmployeeDiscountUsageRepository employeeDiscountUsageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDiscountUsageDTO> findAll() {
        List<EmployeeDiscountUsage> employeeDiscountUsages= this.employeeDiscountUsageRepository.findAll();

        return employeeDiscountUsages.stream()
                .map(employeeDiscountUsage -> this.modelMapper.map(employeeDiscountUsage, EmployeeDiscountUsageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public  List<EmployeeDiscountUsageDTO> findByActiveTrue() {
        List<EmployeeDiscountUsage> activeUsersdiscounts = this.employeeDiscountUsageRepository.findByActiveTrue();
        
        return activeUsersdiscounts.stream()
                .map(discount -> modelMapper.map(discount, EmployeeDiscountUsageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDiscountUsageDTO findById(Long id) {
        EmployeeDiscountUsage employeeDiscountUsage = this.employeeDiscountUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee discount not found"));

        return this.modelMapper.map(employeeDiscountUsage, EmployeeDiscountUsageDTO.class);
    }

    @Override
    public EmployeeDiscountUsageDTO save(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (user.getUserType() == UserType.EMPLOYEE && user.isActive()) {
            EmployeeDiscountUsage newEntity = new EmployeeDiscountUsage();
            newEntity.setEmployee(user);

            EmployeeDiscountUsage savedEntity = this.employeeDiscountUsageRepository.save(newEntity);

            return this.modelMapper.map(savedEntity, EmployeeDiscountUsageDTO.class);
        }
        return null;
    }

    @Override
    public EmployeeDiscountUsageDTO update(Long id, EmployeeDiscountUsageDTO dto){
        EmployeeDiscountUsage entity = this.employeeDiscountUsageRepository.getByEmployeeId(id);

        if (entity.isActive()) {
            entity.setAcumulatedAmount(entity.getAcumulatedAmount().add(dto.getAcumulatedAmount()));
            EmployeeDiscountUsage updatedEntity = this.employeeDiscountUsageRepository.save(entity);
            return this.modelMapper.map(updatedEntity, EmployeeDiscountUsageDTO.class);
        }
        return null;
    }

    @Override
    public EmployeeDiscountUsageDTO deactivate(Long id) {
        EmployeeDiscountUsage employeeDiscount = this.employeeDiscountUsageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee discount amount not found"));

        employeeDiscount.setActive(false);
        EmployeeDiscountUsage savedEmployeeDiscountAmount = this.employeeDiscountUsageRepository.save(employeeDiscount);

        return this.modelMapper.map(savedEmployeeDiscountAmount, EmployeeDiscountUsageDTO.class);
    }
}
