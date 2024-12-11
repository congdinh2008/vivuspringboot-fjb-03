package com.congdinh.vivuspringboot.controllers;

import java.util.UUID;

import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.congdinh.vivuspringboot.dtos.category.CategoryCreateUpdateDTO;
import com.congdinh.vivuspringboot.dtos.category.CategoryDTO;
import com.congdinh.vivuspringboot.services.ICategoryService;

import jakarta.validation.Valid;

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
        model.addAttribute("isShow", true);
        return "manager/category/index";
    }

    @GetMapping("/{categoryId}/products/{productId}")
    public String findAll(
        @PathVariable UUID categoryId,
        @PathVariable UUID productId,
        Model model) {
        var categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "manager/category/index";
    }

    // Show form
    @GetMapping("/create")
    public String create(Model model) {
        // Create new categoryCreateUpdateDTO
        var categoryCreateUpdateDTO = new CategoryCreateUpdateDTO();

        // Passing to view to validate data
        model.addAttribute("category", categoryCreateUpdateDTO);
        return "manager/category/create";
    }

    // Collect data from view by Form
    @PostMapping("/create")
    public String create(@ModelAttribute("category") @Valid CategoryCreateUpdateDTO categoryCreateUpdateDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // Validate data from form
        if (bindingResult.hasErrors()) {
            return "manager/category/create";
        }

        // Create category
        CategoryDTO result = null;
        try {
            result = categoryService.create(categoryCreateUpdateDTO);
        } catch (Exception e) {
            // Passing error message to view creating
            model.addAttribute("error", e.getMessage());
            return "manager/category/create";
        }

        if (result != null) {
            // Redirect to index of categories with success message
            redirectAttributes.addFlashAttribute("success", "Create category successfully");
            return "redirect:/manager/categories";
        } else {
            // Passing error message to view creating
            model.addAttribute("error", "Create category failed");
            return "redirect:/manager/categories/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable UUID id,
            Model model) {
        // Get category from db
        var categoryDTO = categoryService.findById(id);

        // Passing to view to validate data
        model.addAttribute("category", categoryDTO);
        return "manager/category/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable UUID id,
            @ModelAttribute("category") @Valid CategoryCreateUpdateDTO categoryCreateUpdateDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // Validate data from form
        if (bindingResult.hasErrors()) {
            return "manager/category/edit";
        }

        // Update category
        CategoryDTO result = null;
        try {
            result = categoryService.update(id, categoryCreateUpdateDTO);
        } catch (Exception e) {
            // Passing error message to view creating
            model.addAttribute("error", e.getMessage());
            model.addAttribute("category", categoryCreateUpdateDTO);

            return "manager/category/edit";
        }

        if (result != null) {
            // Redirect to index of categories with success message
            redirectAttributes.addFlashAttribute("success", "Update category successfully");
            return "redirect:/manager/categories";
        } else {
            // Passing error message to view update
            model.addAttribute("error", "Update category failed");
            return "manager/category/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
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
