package com.congdinh.vivuspringboot.dtos.category;

import java.util.UUID;

public class CategoryDTO {
    private UUID id;

    private String name;

    private String description;

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

    public CategoryDTO(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryDTO() {
    }
}
