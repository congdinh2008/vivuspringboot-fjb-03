package com.congdinh.vivuspringboot.services;

import java.util.List;
import java.util.UUID;

import com.congdinh.vivuspringboot.dtos.product.ProductCreateUpdateDTO;
import com.congdinh.vivuspringboot.dtos.product.ProductDTO;

public interface IProductService {
/**
     * Get all Product
     * @return List<ProductDTO>
     */
    List<ProductDTO> findAll();

    /**
     * Get Product by id
     * @param id The id of Product
     * @return ProductDTO by id
     */
    ProductDTO findById(UUID id);

    
    /**
     * Create Product
     * @param productCreateUpdateDTO ProductCreateUpdateDTO need to create
     * @return ProductDTO created
     */
    ProductDTO create(ProductCreateUpdateDTO productCreateUpdateDTO);

    /**
     * Update Product
     * @param id The id of Product
     * @param productCreateUpdateDTO ProductCreateUpdateDTO need to update
     * @return ProductDTO updated
     */
    ProductDTO update(UUID id, ProductCreateUpdateDTO productCreateUpdateDTO);

    /**
     * Delete Product
     * @param id The id of Product
     * @return True if delete success, otherwise false
     */
    boolean delete(UUID id);

    /**
     * Delete Product
     * @param id The id of Product
     * @param isSoftDelete True if soft delete, otherwise false
     * @return True if delete success, otherwise false
     */
    boolean delete(UUID id, boolean isSoftDelete);
}
