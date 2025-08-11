package com.vortex.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.vortex.bean.LoginRequest;
import com.vortex.bean.RegisterRequest;
import com.vortex.common.service.CommonService;
import com.vortex.model.User;
import com.vortex.security.JwtUtils;
import com.vortex.security.UserDetailsImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    CommonService commonService;

    @GetMapping("/serverCheck")
    public ResponseEntity<?> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

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
    public ResponseEntity<?> registerCommonUser(@Valid @RequestBody RegisterRequest signUpRequest) {
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
    public ResponseEntity<?> deleteUserById(@Valid @RequestBody Long idUser) {
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