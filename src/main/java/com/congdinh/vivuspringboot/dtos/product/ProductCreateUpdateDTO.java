package com.congdinh.vivuspringboot.dtos.product;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductCreateUpdateDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @Length(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Active is required")
    private boolean active;
    
    @NotNull(message = "Price is required")
    private double price;
    
    @NotNull(message = "Stock is required")
    private int stock;

    @NotNull(message = "Category is required")
    private UUID categoryId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public ProductCreateUpdateDTO(UUID id,
            @NotBlank(message = "Name is required") @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters") String name,
            @Length(max = 500, message = "Description must be less than 500 characters") String description,
            @NotNull(message = "Active is required") boolean active, double price, int stock, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public ProductCreateUpdateDTO() {
    }

    public ProductCreateUpdateDTO(
            @NotBlank(message = "Name is required") @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters") String name,
            @Length(max = 500, message = "Description must be less than 500 characters") String description,
            @NotNull(message = "Active is required") boolean active, double price, int stock, UUID categoryId) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }
}
