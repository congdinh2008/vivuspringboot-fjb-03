package com.congdinh.vivuspringboot.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        boolean result = false;
        try {
            result = categoryService.delete(id);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Delete category failed");
            return "redirect:/manager/categories";
        }

        if (result) {
            // Redirect to index of categories
            redirectAttributes.addFlashAttribute("success",
                    "Delete category successfully");
            return "redirect:/manager/categories";
        } else {
            // Passing error message to index
            redirectAttributes.addFlashAttribute("error",
                    "Delete category failed");
            return "redirect:/manager/categories";
        }
    }

    // Create
    // Get
    /// Post
    /// 
    // Edit
    // Get
    /// Post
}
