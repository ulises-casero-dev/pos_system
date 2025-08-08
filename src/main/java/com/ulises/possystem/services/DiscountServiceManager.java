package com.ulises.possystem.services;

import com.ulises.possystem.dto.discount.DiscountCreateDTO;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.discount.DiscountUpdateDTO;
import com.ulises.possystem.entities.Category;
import com.ulises.possystem.entities.Discount;
import com.ulises.possystem.entities.Product;
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
    public DiscountDTO findById(Long id) {
        Discount discountEntity = this.discountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount not found"));

        return this.modelMapper.map(discountEntity, DiscountDTO.class);
    }

    @Override
    public DiscountDTO save(DiscountCreateDTO createDto) {
        Discount newDiscount = new Discount();

        newDiscount.setDescription(createDto.getDesctiprion());
        newDiscount.setIsGeneral(createDto.getIsGeneral());
        newDiscount.setLimit(createDto.getLimitAmount());
        newDiscount.setAplicativeUserType(createDto.getAplicativeUserType());

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
    public DiscountDTO update(DiscountUpdateDTO discountUpdateDto) {
        return null;
    }

    @Override
    public DiscountDTO deactivateDiscount(Long id) {
        return null;
    }

    @Override
    public DiscountDTO activateDiscount(Long id) {
        return null;
    }
}
