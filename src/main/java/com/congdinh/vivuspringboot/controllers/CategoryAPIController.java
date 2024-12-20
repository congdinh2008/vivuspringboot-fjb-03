package com.congdinh.vivuspringboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.congdinh.vivuspringboot.dtos.category.CategoryCreateUpdateDTO;
import com.congdinh.vivuspringboot.services.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPIController {
    private final ICategoryService categoryService;

    public CategoryAPIController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid CategoryCreateUpdateDTO categoryCreateUpdateDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var result = categoryService.create(categoryCreateUpdateDTO);

        return ResponseEntity.ok(result);
    }
}
