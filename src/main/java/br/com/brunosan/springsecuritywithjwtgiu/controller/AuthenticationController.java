package br.com.brunosan.springsecuritywithjwtgiu.controller;

import br.com.brunosan.springsecuritywithjwtgiu.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService service;
    
    @PostMapping("authenticate")
    public String auth(Authentication authentication) {
        return service.authenticate(authentication);
    }
}
