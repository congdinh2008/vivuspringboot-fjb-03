package com.congdinh.vivuspringboot.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congdinh.vivuspringboot.dtos.category.CategoryCreateUpdateDTO;
import com.congdinh.vivuspringboot.dtos.category.CategoryDTO;
import com.congdinh.vivuspringboot.entities.Category;
import com.congdinh.vivuspringboot.repositories.ICategoryRepository;

import jakarta.persistence.criteria.Predicate;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        // Get all category entities
        var categories = categoryRepository.findAll();

        // Convert to DTO
        var categoryDTOs = categories.stream().map(item -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(item.getId());
            categoryDTO.setName(item.getName());
            categoryDTO.setDescription(item.getDescription());
            return categoryDTO;
        }).toList();

        // Return data
        return categoryDTOs;
    }

    @Override
    public CategoryDTO findById(UUID id) {
        var category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new IllegalArgumentException("Category is null");
        }

        // Convert to DTO
        var categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        // Return dto
        return categoryDTO;
    }

    @Override
    public Page<CategoryDTO> searchAll(String keyword, Pageable pageable) {
        Specification<Category> spec = (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return null;
            }
            // name LIKE %keyword%
            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + keyword.toLowerCase() + "%");

            // description LIKE %keyword%
            Predicate descriptionPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + keyword.toLowerCase() + "%");

            // name LIKE %keyword% OR description LIKE %keyword%
            return criteriaBuilder.or(namePredicate, descriptionPredicate);
        };

        var categories = categoryRepository.findAll(spec, pageable);

        // Convert to DTO
        var categoryDTOs = categories.map(item -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(item.getId());
            categoryDTO.setName(item.getName());
            categoryDTO.setDescription(item.getDescription());
            return categoryDTO;
        });

        // Return data
        return categoryDTOs;
    }

    @Override
    public Page<CategoryDTO> searchAll(String keyword, int active, Pageable pageable) {
        Specification<Category> spec = (root, query, criteriaBuilder) -> {
            if ((keyword == null || keyword.isBlank()) && (active < 0 || active > 2)) {
                return null;
            }
            // name LIKE %keyword%
            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + keyword.toLowerCase() + "%");

            // description LIKE %keyword%
            Predicate descriptionPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + keyword.toLowerCase() + "%");

            if (active == 2) {
                // name LIKE %keyword% OR description LIKE %keyword%
                return criteriaBuilder.or(namePredicate, descriptionPredicate);
            }

            Predicate nameOrDescription = criteriaBuilder.or(namePredicate, descriptionPredicate);
            Predicate activePredicate = criteriaBuilder.equal(root.get("active"), active == 1);

            // name LIKE %keyword% OR description LIKE %keyword% AND active = active
            return criteriaBuilder.and(nameOrDescription, activePredicate);
        };

        var categories = categoryRepository.findAll(spec, pageable);

        // Convert to DTO
        var categoryDTOs = categories.map(item -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(item.getId());
            categoryDTO.setName(item.getName());
            categoryDTO.setDescription(item.getDescription());
            return categoryDTO;
        });

        // Return data
        return categoryDTOs;
    }

    @Override
    public CategoryDTO create(CategoryCreateUpdateDTO categoryCreateUpdateDTO) {
        // Check null object
        if (categoryCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Category is null");
        }

        // Check unique name
        var existingCategory = categoryRepository.findByName(categoryCreateUpdateDTO.getName());

        if (existingCategory != null) {
            throw new IllegalArgumentException("Category name is existed");
        }

        // Convert to category
        var category = new Category();
        category.setName(categoryCreateUpdateDTO.getName());
        category.setDescription(categoryCreateUpdateDTO.getDescription());
        category.setActive(categoryCreateUpdateDTO.isActive());
        category.setInsertedAt(ZonedDateTime.now());

        // Save to database
        var newCategory = categoryRepository.save(category);

        // Convert to DTO
        var categoryDTO = new CategoryDTO();
        categoryDTO.setId(newCategory.getId());
        categoryDTO.setName(newCategory.getName());
        categoryDTO.setDescription(newCategory.getDescription());

        // Return dto
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(UUID id, CategoryCreateUpdateDTO categoryCreateUpdateDTO) {
        // Check null object
        if (categoryCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Category is null");
        }

        // Check if category not exsiting
        var existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory == null) {
            throw new IllegalArgumentException("Category is not existed");
        }

        // Check unique name if not the same id
        var existingCategorySameName = categoryRepository.findByName(categoryCreateUpdateDTO.getName());

        if (existingCategorySameName != null && !existingCategorySameName.getId().equals(id)) {
            throw new IllegalArgumentException("Category name is existed");
        }

        // Convert to category to update
        existingCategory.setName(categoryCreateUpdateDTO.getName());
        existingCategory.setDescription(categoryCreateUpdateDTO.getDescription());
        existingCategory.setActive(categoryCreateUpdateDTO.isActive());
        existingCategory.setUpdatedAt(ZonedDateTime.now());

        // Save to database
        var updatedCategory = categoryRepository.save(existingCategory);
        // Return true if success, otherwise false

        // Convert to DTO
        var categoryDTO = new CategoryDTO();
        categoryDTO.setId(updatedCategory.getId());
        categoryDTO.setName(updatedCategory.getName());
        categoryDTO.setDescription(updatedCategory.getDescription());

        // Return dto
        return categoryDTO;
    }

    @Override
    public boolean delete(UUID id) {
        var existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category is not existed");
        }

        categoryRepository.delete(existingCategory);

        var result = categoryRepository.existsById(id);

        return !result;
    }

    @Override
    public boolean delete(UUID id, boolean isSoftDelete) {
        var existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category is not existed");
        }

        if (isSoftDelete) {
            existingCategory.setDeletedAt(ZonedDateTime.now());
            categoryRepository.save(existingCategory);
            return true;
        } else {
            return delete(id);
        }
    }
}
