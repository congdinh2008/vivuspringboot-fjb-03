package com.congdinh.vivuspringboot.dtos.auth;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {
    @NotBlank(message = "First name is required")
    @Length(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Length(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Length(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Length(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters")
    private String phoneNumber;

    private String avatar;

    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;
    
    @NotBlank(message = "Confirm password is required")
    @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public RegisterRequestDTO(
            @NotBlank(message = "First name is required") @Length(min = 3, max = 50, message = "First name must be between 3 and 50 characters") String firstName,
            @NotBlank(message = "Last name is required") @Length(min = 3, max = 50, message = "Last name must be between 3 and 50 characters") String lastName,
            @NotBlank(message = "Username is required") @Length(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
            @NotBlank(message = "Phone number is required") @Length(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters") String phoneNumber,
            String avatar,
            @NotBlank(message = "Password is required") @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters") String password,
            @NotBlank(message = "Confirm password is required") @Length(min = 8, max = 20, message = "Password must be between 8 and 20 characters") String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RegisterRequestDTO() {
    }
}
