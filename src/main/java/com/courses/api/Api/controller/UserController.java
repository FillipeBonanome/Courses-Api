package com.courses.api.Api.controller;

import com.courses.api.Api.dto.CreateUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody CreateUserDTO userDTO) {
        userRepository.save(new User(userDTO));
    }

}
