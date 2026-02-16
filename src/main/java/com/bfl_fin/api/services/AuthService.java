package com.bfl_fin.api.services;

import com.bfl_fin.api.dtos.request.RegisterRequest;
import com.bfl_fin.api.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    User register(RegisterRequest request);
}