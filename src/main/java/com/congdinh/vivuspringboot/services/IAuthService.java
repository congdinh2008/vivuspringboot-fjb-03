package com.congdinh.vivuspringboot.services;

import com.congdinh.vivuspringboot.dtos.auth.RegisterRequestDTO;

public interface IAuthService {
    boolean register(RegisterRequestDTO registerRequest);
}
