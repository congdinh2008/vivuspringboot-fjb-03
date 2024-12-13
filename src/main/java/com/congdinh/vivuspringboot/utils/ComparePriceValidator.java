package com.congdinh.vivuspringboot.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePriceValidator implements ConstraintValidator<ComparePrice, Object> {
    private double min;
    private double max;

    @Override
    public void initialize(ComparePrice constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values; use @NotNull for mandatory checks
        }

        try {
            // Assuming the annotated class has `getMinPrice` and `getMaxPrice` methods
            double minPrice = (double) value.getClass().getMethod("getMinPrice").invoke(value);
            double maxPrice = (double) value.getClass().getMethod("getMaxPrice").invoke(value);

            // Validation logic
            return minPrice >= min && maxPrice <= max && minPrice <= maxPrice;
        } catch (Exception e) {
            return false; // Invalid if reflection fails
        }
    }
}