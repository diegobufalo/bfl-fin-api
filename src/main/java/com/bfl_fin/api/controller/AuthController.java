package com.bfl_fin.api.controller;

import com.bfl_fin.api.dtos.request.LoginRequest;
import com.bfl_fin.api.dtos.request.RegisterRequest;
import com.bfl_fin.api.dtos.response.AuthenticationResponse;
import com.bfl_fin.api.model.User;
import com.bfl_fin.api.security.JwtService;
import com.bfl_fin.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.register(request);
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        User user = (User) userService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);
    }
}
