package com.bfl_fin.api.services;

import com.bfl_fin.api.dtos.request.RegisterRequest;
import com.bfl_fin.api.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {

    User findById(UUID id);

    User register(RegisterRequest request);
}
