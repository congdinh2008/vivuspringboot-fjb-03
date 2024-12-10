package com.congdinh.vivuspringboot.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.congdinh.vivuspringboot.services.ICategoryService;

@Controller
@RequestMapping("/manager/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String findAll(Model model) {
        var categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "manager/category/index";
    }

    @GetMapping("/{id}")
    public String findAll(
            @PathVariable("id") UUID id,
            Model model) {
        var category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "manager/category/details";
    }

    // Create
    // Get
    /// Post
    /// 
    // Edit
    // Get
    /// Post
}
