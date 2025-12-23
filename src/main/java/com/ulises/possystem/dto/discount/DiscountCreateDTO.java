package com.ulises.possystem.dto.discount;

import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.enums.UserType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class DiscountCreateDTO {

    @NotBlank(message = "The description can't be empty")
    private String description;

    @NotNull(message = "The discount amount can't be null")
    @Min(value = 1, message = "Amount must be at least 1")
    @Max(value = 99, message = "Amount must be less than 100")
    private Double amount;

    @NotNull(message = "The discount type can't be null")
    private DiscountType discountType;

    @NotNull(message = "The limit amount can't be null")
    @Min(value = 1, message = "Limit amount must be at least 1")
    private Double limitAmount;

    private Long categoryId;
    private Long productId;



    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDiscountType(DiscountType Type) {
        discountType = Type;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }
}
