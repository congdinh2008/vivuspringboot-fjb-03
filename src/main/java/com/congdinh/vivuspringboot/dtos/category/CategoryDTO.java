package com.congdinh.vivuspringboot.dtos.category;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDTO {
    private UUID id;

    private String name;

    private String description;
}
