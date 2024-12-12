package com.congdinh.vivuspringboot.dtos.category;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class CategoryCreateUpdateDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @Length(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Active is required")
    private boolean active;

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

    public CategoryCreateUpdateDTO(UUID id,
            @NotBlank(message = "Name is required") @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters") String name,
            @Length(max = 500, message = "Description must be less than 500 characters") String description,
            @NotNull(message = "Active is required") boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public CategoryCreateUpdateDTO(
            @NotBlank(message = "Name is required") @Length(min = 2, max = 255, message = "Name must be between 2 and 255 characters") String name,
            @Length(max = 500, message = "Description must be less than 500 characters") String description,
            @NotNull(message = "Active is required") boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public CategoryCreateUpdateDTO() {
    }
}
