package com.congdinh.vivuspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.congdinh.vivuspringboot.dtos.auth.RegisterRequestDTO;
import com.congdinh.vivuspringboot.services.IAuthService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    // MVC: Login chi can GetMapping
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        var registerRequestDTO = new RegisterRequestDTO();
        model.addAttribute("register", registerRequestDTO);
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid RegisterRequestDTO registerRequestDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        var result = authService.register(registerRequestDTO);
        if (result) {
            return "redirect:/auth/login";
        } else {
            model.addAttribute("error", "Register failed");
            return "auth/register";
        }
    }
}
