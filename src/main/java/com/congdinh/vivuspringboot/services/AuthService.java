package com.congdinh.vivuspringboot.services;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congdinh.vivuspringboot.dtos.auth.RegisterRequestDTO;
import com.congdinh.vivuspringboot.entities.User;
import com.congdinh.vivuspringboot.repositories.IUserRepository;

@Service
@Transactional
public class AuthService implements IAuthService, UserDetailsService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = Set.of();

        // Check if user has roles and map them to authorities
        if (user.getRoles() != null) {
            authorities = user.getRoles().stream()
                    .map(role -> "ROLE_" + role.getName())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }

        // Return a UserDetails object with username, password and authorities (empty if
        // no roles)
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    @Override
    public boolean register(RegisterRequestDTO registerRequest) {
        // Check null object
        if (registerRequest == null) {
            throw new IllegalArgumentException("User to register is null");
        }

        // Tim user da ton tai voi username hoac phone number
        var existingUser = userRepository.findByUsernameOrPhoneNumber(
                registerRequest.getUsername(), registerRequest.getPhoneNumber());

        // Check if username is already taken
        if (existingUser != null && existingUser.getUsername().equals(
                registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if phone number is already taken
        if (existingUser != null && existingUser.getPhoneNumber()
                .equals(registerRequest.getPhoneNumber())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if password and confirm password are the same
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and confirm password are not the same");
        }

        // Convert DTO to entity
        var user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAvatar(registerRequest.getAvatar());
        user.setInsertedAt(ZonedDateTime.now());
        user.setActive(true);

        // Encode password
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Save user
        var newUser = userRepository.save(user);

        // Check if user is saved
        var result = userRepository.existsById(newUser.getId());
        return result;
    }
}
