package com.ulises.possystem.discount.service;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Discount;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.exception.business.DiscountTypeNotAllowedForProductException;
import com.ulises.possystem.exception.business.InvalidDiscountScopeException;
import com.ulises.possystem.exception.business.ProductInactiveException;
import com.ulises.possystem.exception.notFound.ResourceNotFoundException;
import com.ulises.possystem.repositories.CategoryRepository;
import com.ulises.possystem.repositories.DiscountRepository;
import com.ulises.possystem.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceManager implements DiscountService{
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DiscountDTO> findAll(){
        List<Discount> discounts = this.discountRepository.findAll();

        return discounts.stream()
                .map(discount -> this.modelMapper.map(discount, DiscountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByDiscountType(DiscountType discountType){
        List<Discount> discounts = this.discountRepository.findByDiscountType(discountType);
        return discounts.stream()
                .map(discount -> this.modelMapper.map(discount, DiscountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> findByDicountStatus(boolean status){
        List<Discount> discounts = this.discountRepository.findByDicountStatus(status);

        return discounts.stream()
                .map(discount -> this.modelMapper.map(discount, DiscountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDTO findById(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found"));

        return this.modelMapper.map(discountEntity, DiscountDTO.class);
    }

    @Override
    public DiscountDTO save(DiscountCreateDTO createDto) {

        System.out.println("DTO productId: " + createDto.getProductId());

        if (createDto.getProductId() != null && this.productRepository.existsByIdAndActiveFalse(createDto.getProductId())) {
            throw new ProductInactiveException(createDto.getProductId());
        }
        else if ((createDto.getDiscountType().equals(DiscountType.CUSTOMER) || createDto.getDiscountType().equals(DiscountType.EMPLOYEE)) && createDto.getProductId() != null){
            throw new DiscountTypeNotAllowedForProductException();
        }
        else if (createDto.getDiscountType().equals(DiscountType.GENERAL) && createDto.getProductId() == null) {
            throw new InvalidDiscountScopeException();
        }

        Discount newDiscount = new Discount();

        newDiscount.setDescription(createDto.getDescription());
        newDiscount.setAmount(createDto.getAmount());
        newDiscount.setDiscountType(createDto.getDiscountType());
        newDiscount.setLimitAmount(createDto.getLimitAmount());



        if (createDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(createDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found."));

            newDiscount.setCategory(category);
        }
        if (createDto.getProductId() != null) {
            Product product = productRepository.findById(createDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            newDiscount.setProduct(product);
        }

        Discount savedDiscount = this.discountRepository.save(newDiscount);

        return this.modelMapper.map(savedDiscount, DiscountDTO.class);
    }

    @Override
    public DiscountDTO patchDiscount(Long id, DiscountUpdateDTO discountUpdateDto) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        if (discountUpdateDto.getDescription() != null) discountEntity.setDescription(discountUpdateDto.getDescription());
        if (discountUpdateDto.getAmount() != null) discountEntity.setAmount(discountUpdateDto.getAmount());
        if (discountUpdateDto.getLimitAmount() != null) discountEntity.setLimitAmount(discountUpdateDto.getLimitAmount());

        if (discountUpdateDto.getCategoryId() != null) {
            Category category = this.categoryRepository.findById(discountUpdateDto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
            discountEntity.setCategory(category);
        }

        if(discountUpdateDto.getProductId() != null) {
            Product product = this.productRepository.findById(discountUpdateDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
            discountEntity.setProduct(product);
        }

        Discount updatedDiscount = this.discountRepository.save(discountEntity);

        return this.modelMapper.map(updatedDiscount, DiscountDTO.class);
    }

    @Override
    public void deactivateDiscount(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        this.discountRepository.updateActivateStatus(false, id);
    }

    @Override
    public void activateDiscount(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        this.discountRepository.updateActivateStatus(true, id);
    }
}
