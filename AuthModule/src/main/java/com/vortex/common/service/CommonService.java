package com.vortex.common.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vortex.bean.RegisterRequest;
import com.vortex.model.User;
import com.vortex.repository.TypeRoleRepository;
import com.vortex.repository.UserRepository;
import com.vortex.security.SecurityConfig;

@Service
public class CommonService {
	
	private final UserRepository userRepository;
	
	private final TypeRoleRepository TypeRoleRepository;
	
	private final SecurityConfig securityConfig;
	
	public CommonService(UserRepository userRepository, SecurityConfig securityConfig, TypeRoleRepository TypeRoleRepository) {
	    this.userRepository = userRepository;
	    this.securityConfig = securityConfig;
	    this.TypeRoleRepository = TypeRoleRepository;
	}

	public boolean existAlias(String alias) {
		return userRepository.existsByAlias(alias);
	}
	
	public boolean existByid(long id) {
		return userRepository.existsById(id);
	}
	
	public void saveCommonUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setAlias(registerRequest.getAlias());
        user.setPassword(securityConfig.passwordEncoder().encode(registerRequest.getPassword()));
        user.setRole(TypeRoleRepository.findById(1).orElseThrow());
        user.setCreationTime(LocalDateTime.now());
        userRepository.save(user);

	}
	
	public void deleteUserById(long id) {
        userRepository.deleteById(id);
	}
	  

}
