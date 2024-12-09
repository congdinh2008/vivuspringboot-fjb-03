package com.congdinh.vivuspringboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congdinh.vivuspringboot.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {
    // JPA Query Method by name
    Category findByName(String name);
}
