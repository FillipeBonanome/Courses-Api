package com.courses.api.Api.controller;

import com.courses.api.Api.dto.CreateUserDTO;
import com.courses.api.Api.dto.ReadUserDTO;
import com.courses.api.Api.dto.SimpleReadUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReadUserDTO> register(@RequestBody @Valid CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    //TODO --> Refactor
    @GetMapping("{id}")
    public ResponseEntity<Object> getById(Authentication authentication, @PathVariable(name = "id") Long id) {
        var authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        //Testing some stuff
        Optional<User> userOptional = (Optional<User>) authentication.getPrincipal();
        User user = userOptional.get();
        if(isAdmin || user.getId().equals(id)) {
            return ResponseEntity.ok(userService.getById(id));
        }

        return ResponseEntity.ok(new SimpleReadUserDTO(userService.getById(id).name()));
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping
    public String test(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "Acesso negado. Por favor, autentique-se.";
        }

        var authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "UM ADMIN";
        } else {
            return "NAO EH ADMIN";
        }
    }

}
