package br.com.brunosan.springsecuritywithjwtgiu.service;

import br.com.brunosan.springsecuritywithjwtgiu.repository.UserRepository;
import br.com.brunosan.springsecuritywithjwtgiu.security.UserAuthenticated;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository repository;
    
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
            .map(UserAuthenticated::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
