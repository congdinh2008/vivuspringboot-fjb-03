package com.congdinh.vivuspringboot.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.congdinh.vivuspringboot.dtos.category.CategoryCreateUpdateDTO;
import com.congdinh.vivuspringboot.dtos.category.CategoryDTO;

public interface ICategoryService {
    /**
     * Get all Category
     * @return List<CategoryDTO>
     */
    List<CategoryDTO> findAll();

    /**
     * Get Category by id
     * @param id The id of Category
     * @return CategoryDTO by id
     */
    CategoryDTO findById(UUID id);

    /**
     * Search Category by keyword
     * @param keyword The keyword to search
     * @return List<CategoryDTO> by keyword
     */
    List<CategoryDTO> searchAll(String keyword, Pageable pageable);
    
    /**
     * Create Category
     * @param categoryCreateUpdateDTO CategoryCreateUpdateDTO need to create
     * @return CategoryDTO created
     */
    CategoryDTO create(CategoryCreateUpdateDTO categoryCreateUpdateDTO);

    /**
     * Update Category
     * @param id The id of Category
     * @param categoryCreateUpdateDTO CategoryCreateUpdateDTO need to update
     * @return CategoryDTO updated
     */
    CategoryDTO update(UUID id, CategoryCreateUpdateDTO categoryCreateUpdateDTO);

    /**
     * Delete Category
     * @param id The id of Category
     * @return True if delete success, otherwise false
     */
    boolean delete(UUID id);

    /**
     * Delete Category
     * @param id The id of Category
     * @param isSoftDelete True if soft delete, otherwise false
     * @return True if delete success, otherwise false
     */
    boolean delete(UUID id, boolean isSoftDelete);
}
