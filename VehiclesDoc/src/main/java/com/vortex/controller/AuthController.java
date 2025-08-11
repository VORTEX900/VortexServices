package com.vortex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.security.JwtUtils;
import com.vortex.security.UserDetailsImpl;

@RestController
@RequestMapping("/authVehicles")
public class AuthController {

    private final JwtUtils jwtUtils;
    
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    	
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.alias(), loginRequest.password));
    	
        String token = jwtUtils.generateJwtToken((UserDetailsImpl) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @GetMapping("/serverCheck")
    public ResponseEntity<?> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }

    public record LoginRequest(String alias, String password) {}
    public record JwtResponse(String token) {}
}