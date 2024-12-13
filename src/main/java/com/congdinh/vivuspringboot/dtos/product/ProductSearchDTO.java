package com.congdinh.vivuspringboot.dtos.product;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.congdinh.vivuspringboot.utils.ComparePrice;

@ComparePrice(min = 10.0, max = 1000.0, message = "Price range must be between 10.0 and 1000.0, and minPrice must be less than or equal to maxPrice.")
public class ProductSearchDTO {
    @Length(min = 3, max = 50, message = "Keyword must be between 3 and 50 characters")
    private String keyword;

    @Range(min = 0, max = 1000000, message = "Min price must be between 0 and 1000000")
    private double minPrice;

    @Range(min = 0, max = 1000000, message = "Max price must be between 0 and 1000000")
    private double maxPrice;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
