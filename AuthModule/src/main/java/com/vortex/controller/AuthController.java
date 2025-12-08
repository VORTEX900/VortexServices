package com.vortex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.bean.LoginRequest;
import com.vortex.bean.RegisterRequest;
import com.vortex.common.service.CommonService;
import com.vortex.security.JwtUtils;
import com.vortex.security.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;
    
    private final CommonService commonService;
    
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, CommonService commonService) {
    	this.authenticationManager = authenticationManager;
    	this.jwtUtils = jwtUtils;
    	this.commonService = commonService;
    }

    @GetMapping("/serverCheck")
    public ResponseEntity<Map<String, Object>> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getAlias(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((UserDetailsImpl) authentication.getPrincipal());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("alias", loginRequest.getAlias());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registerCommonUser")
    public ResponseEntity<Map<String, String>> registerCommonUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        // Controlla se l'alias è già preso (dovrai implementare questa logica nel service o repo)
        if (commonService.existAlias(signUpRequest.getAlias())) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("error", "Alias già in uso!"));
        }

        // Crea nuovo utente e cripta la password
        commonService.saveCommonUser(signUpRequest);

        return ResponseEntity.ok(Map.of("message", "Utente registrato con successo!"));
    }
    
    @PostMapping("/deleteUserById")
    public ResponseEntity<Map<String, String>> deleteUserById(@Valid @RequestBody Long idUser) {
        // Controlla se l'alias è già preso (dovrai implementare questa logica nel service o repo)
        if (!commonService.existByid(idUser)){
            return ResponseEntity
                .badRequest()
                .body(Map.of("error", "Utente NON trovato!!!"));
        }

        // Crea nuovo utente e cripta la password
        commonService.deleteUserById(idUser);

        return ResponseEntity.ok(Map.of("message", "Utente rimosso con successo!"));
    }
 
}