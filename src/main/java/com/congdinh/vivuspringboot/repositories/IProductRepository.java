package com.congdinh.vivuspringboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.congdinh.vivuspringboot.entities.Product;

public interface IProductRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);
}
