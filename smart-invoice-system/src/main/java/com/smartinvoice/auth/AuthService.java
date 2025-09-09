package com.smartinvoice.auth;

import com.smartinvoice.dto.AuthRequest;
import com.smartinvoice.dto.AuthResponse;
import com.smartinvoice.dto.RegisterRequest;
import com.smartinvoice.entity.AppUser;
import com.smartinvoice.repository.AppUserRepository;
import com.smartinvoice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest req) {
        var user = users.findByEmail(req.email()).orElseThrow();
        if (!passwordEncoder.matches(req.password(), user.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name());
    }

    public AuthResponse register(RegisterRequest req) {
        if (users.existsByEmail(req.email())) throw new RuntimeException("Email exists");
        AppUser.Role role = AppUser.Role.USER;
        if ("MANAGER".equalsIgnoreCase(req.role())) role = AppUser.Role.MANAGER;
        AppUser u = AppUser.builder().email(req.email()).password(passwordEncoder.encode(req.password())).role(role).build();
        users.save(u);
        String token = jwtUtil.generateToken(u.getEmail(), u.getRole().name());
        return new AuthResponse(token, u.getRole().name());
    }
}
