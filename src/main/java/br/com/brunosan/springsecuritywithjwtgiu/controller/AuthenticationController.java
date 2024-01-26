package br.com.brunosan.springsecuritywithjwtgiu.controller;

import br.com.brunosan.springsecuritywithjwtgiu.service.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    private final AuthenticationService service;
    
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @PostMapping
    public String auth() {
        return service.authenticate();
    }
}
