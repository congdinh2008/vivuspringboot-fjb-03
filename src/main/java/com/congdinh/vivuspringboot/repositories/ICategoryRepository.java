package com.congdinh.vivuspringboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.congdinh.vivuspringboot.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {
    // JPA Query Method by name
    Category findByName(String name);
}
