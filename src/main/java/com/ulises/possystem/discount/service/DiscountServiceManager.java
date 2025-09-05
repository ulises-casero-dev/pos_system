package com.ulises.possystem.discount.service;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Discount;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.exception.ResourceNotFoundException;
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
    public DiscountDTO findById(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found"));

        return this.modelMapper.map(discountEntity, DiscountDTO.class);
    }

    @Override
    public DiscountDTO save(DiscountCreateDTO createDto) {
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
    public DiscountDTO update(Long id, DiscountUpdateDTO discountUpdateDto) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        discountEntity.setDescription(discountUpdateDto.getDescription());
        discountEntity.setAmount(discountUpdateDto.getAmount());
        discountEntity.setLimitAmount(discountUpdateDto.getLimitAmount());

        if(discountUpdateDto.getCategoryId() != null) {
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
    public DiscountDTO deactivateDiscount(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        discountEntity.setActive(false);

        Discount updatedDiscount = this.discountRepository.save(discountEntity);

        return this.modelMapper.map(updatedDiscount, DiscountDTO.class);
    }

    @Override
    public DiscountDTO activateDiscount(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found."));

        discountEntity.setActive(true);

        Discount updatedDiscount = this.discountRepository.save(discountEntity);

        return this.modelMapper.map(updatedDiscount, DiscountDTO.class);
    }
}
