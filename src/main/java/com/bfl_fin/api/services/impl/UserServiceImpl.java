package com.bfl_fin.api.services.impl;

import com.bfl_fin.api.dtos.request.RegisterRequest;
import com.bfl_fin.api.enums.Role;
import com.bfl_fin.api.exception.EmailAlreadyExistsException;
import com.bfl_fin.api.exception.NotFoundException;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.repositories.UserRepository;
import com.bfl_fin.api.services.UserService;
import com.bfl_fin.api.utils.ConstMessages;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstMessages.USER_NOT_FOUND));
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .active(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public @NonNull User loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ConstMessages.USER_NOT_FOUND + email));
    }
}
