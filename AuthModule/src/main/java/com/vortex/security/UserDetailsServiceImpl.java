package com.vortex.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vortex.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        com.vortex.model.User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con alias: " + alias));
        return new UserDetailsImpl(user);
    }
}