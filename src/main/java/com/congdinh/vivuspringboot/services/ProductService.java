package com.congdinh.vivuspringboot.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congdinh.vivuspringboot.dtos.category.CategoryDTO;
import com.congdinh.vivuspringboot.dtos.product.ProductCreateUpdateDTO;
import com.congdinh.vivuspringboot.dtos.product.ProductDTO;
import com.congdinh.vivuspringboot.entities.Product;
import com.congdinh.vivuspringboot.repositories.ICategoryRepository;
import com.congdinh.vivuspringboot.repositories.IProductRepository;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository,
            ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        // Get all product entities
        var products = productRepository.findAll();

        // Convert to DTO
        var productDTOs = products.stream().map(item -> {
            var productDTO = new ProductDTO();
            productDTO.setId(item.getId());
            productDTO.setName(item.getName());
            productDTO.setDescription(item.getDescription());
            productDTO.setPrice(item.getPrice());
            productDTO.setStock(item.getStock());

            // Lay them thong tin category ma product do thuoc ve
            if (item.getCategory() != null) {
                var categoryDTO = new CategoryDTO();
                categoryDTO.setId(item.getCategory().getId());
                categoryDTO.setName(item.getCategory().getName());
                categoryDTO.setDescription(item.getCategory().getDescription());
                productDTO.setCategory(categoryDTO);
            }

            return productDTO;
        }).toList();

        // Return data
        return productDTOs;
    }

    @Override
    public ProductDTO findById(UUID id) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product is null");
        }

        // Convert to DTO
        var productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());

        // Lay them thong tin category ma product do thuoc ve
        if (product.getCategory() != null) {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }

        // Return dto
        return productDTO;
    }

    @Override
    public ProductDTO create(ProductCreateUpdateDTO productCreateUpdateDTO) {
        // Check null object
        if (productCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Product is null");
        }

        // Check unique name
        var existingProduct = productRepository.findByName(productCreateUpdateDTO.getName());

        if (existingProduct != null) {
            throw new IllegalArgumentException("Product name is existed");
        }

        // Check if category exist with categoryId
        var existingCategory = categoryRepository.findById(productCreateUpdateDTO.getCategoryId()).orElse(null);

        if(existingCategory == null){
            throw new IllegalArgumentException("Category not existed");
        }

        // Convert to product
        var product = new Product();
        product.setName(productCreateUpdateDTO.getName());
        product.setDescription(productCreateUpdateDTO.getDescription());
        product.setActive(productCreateUpdateDTO.isActive());
        product.setInsertedAt(ZonedDateTime.now());
        product.setCategory(existingCategory);

        // Save to database
        var newProduct = productRepository.save(product);

        // Convert to DTO
        var productDTO = new ProductDTO();
        productDTO.setId(newProduct.getId());
        productDTO.setName(newProduct.getName());
        productDTO.setDescription(newProduct.getDescription());

        // Lay them thong tin category ma product do thuoc ve
        if (newProduct.getCategory() != null) {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(newProduct.getCategory().getId());
            categoryDTO.setName(newProduct.getCategory().getName());
            categoryDTO.setDescription(newProduct.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }


        // Return dto
        return productDTO;
    }

    @Override
    public ProductDTO update(UUID id, ProductCreateUpdateDTO productCreateUpdateDTO) {
        // Check null object
        if (productCreateUpdateDTO == null) {
            throw new IllegalArgumentException("Product is null");
        }

        // Check if product not exsiting
        var existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            throw new IllegalArgumentException("Product is not existed");
        }

        // Check unique name if not the same id
        var existingProductSameName = productRepository.findByName(productCreateUpdateDTO.getName());

        if (existingProductSameName != null && !existingProductSameName.getId().equals(id)) {
            throw new IllegalArgumentException("Product name is existed");
        }

        // Check if category exist with categoryId
        var existingCategory = categoryRepository.findById(productCreateUpdateDTO.getCategoryId()).orElse(null);

        if(existingCategory == null){
            throw new IllegalArgumentException("Category not existed");
        }

        // Convert to product to update
        existingProduct.setName(productCreateUpdateDTO.getName());
        existingProduct.setDescription(productCreateUpdateDTO.getDescription());
        existingProduct.setActive(productCreateUpdateDTO.isActive());
        existingProduct.setUpdatedAt(ZonedDateTime.now());
        existingProduct.setCategory(existingCategory);

        // Save to database
        var updatedProduct = productRepository.save(existingProduct);
        // Return true if success, otherwise false

        // Convert to DTO
        var productDTO = new ProductDTO();
        productDTO.setId(updatedProduct.getId());
        productDTO.setName(updatedProduct.getName());
        productDTO.setDescription(updatedProduct.getDescription());

        // Lay them thong tin category ma product do thuoc ve
        if (updatedProduct.getCategory() != null) {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setId(updatedProduct.getCategory().getId());
            categoryDTO.setName(updatedProduct.getCategory().getName());
            categoryDTO.setDescription(updatedProduct.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }

        // Return dto
        return productDTO;
    }

    @Override
    public boolean delete(UUID id) {
        var existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product is not existed");
        }

        productRepository.delete(existingProduct);

        var result = productRepository.existsById(id);

        return !result;
    }

    @Override
    public boolean delete(UUID id, boolean isSoftDelete) {
        var existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product is not existed");
        }

        if (isSoftDelete) {
            existingProduct.setDeletedAt(ZonedDateTime.now());
            productRepository.save(existingProduct);
            return true;
        } else {
            return delete(id);
        }
    }
}
