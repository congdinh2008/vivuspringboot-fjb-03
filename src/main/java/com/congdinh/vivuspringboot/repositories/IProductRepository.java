package com.congdinh.vivuspringboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.congdinh.vivuspringboot.entities.Product;

public interface IProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    Product findByName(String name);
}
