package com.courses.api.Api.controller;

import com.courses.api.Api.dto.CreateUserDTO;
import com.courses.api.Api.dto.ReadUserDTO;
import com.courses.api.Api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<ReadUserDTO> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

}
