package com.congdinh.vivuspringboot.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Constraint(validatedBy = ComparePriceValidator.class)
@Target({ ElementType.TYPE }) // Apply at the class level
@Retention(RetentionPolicy.RUNTIME)
public @interface ComparePrice {
    String message() default "Invalid price range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    double min() default 0.0;
    double max() default Double.MAX_VALUE;
}
