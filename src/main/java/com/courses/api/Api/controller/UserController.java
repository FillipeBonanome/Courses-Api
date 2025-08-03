package com.courses.api.Api.controller;

import com.courses.api.Api.dto.user.CreateUserDTO;
import com.courses.api.Api.dto.user.ReadUserDTO;
import com.courses.api.Api.dto.user.SimpleReadUserDTO;
import com.courses.api.Api.dto.user.UpdateUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.infra.exception.UserException;
import com.courses.api.Api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Object> getById(Authentication authentication, @PathVariable(name = "id") UUID id) {
        var authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        //TODO --> Refactor
        var userOptional = (Optional<User>) authentication.getPrincipal();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (isAdmin || user.getId().equals(id)) {
                return ResponseEntity.ok(userService.getById(id));
            }
        }

        return ResponseEntity.ok(new SimpleReadUserDTO(userService.getById(id).name()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Page<ReadUserDTO>> getUsers(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable));
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //TODO --> Refactor
    @PutMapping
    @Transactional
    public ResponseEntity<ReadUserDTO> updateUser(Authentication authentication, @RequestBody UpdateUserDTO userDTO) {
        var userOptional = (Optional<User>) authentication.getPrincipal();
        if (userOptional.isEmpty()) {
            throw new UserException("User not found");
        }

        User user = userOptional.get();
        return ResponseEntity.ok(userService.updateUser(user.getId(), userDTO));
    }

}
