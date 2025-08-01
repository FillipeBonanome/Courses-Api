package com.courses.api.Api.service;

import com.courses.api.Api.dto.CreateUserDTO;
import com.courses.api.Api.dto.ReadUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.infra.exception.DuplicateResourceException;
import com.courses.api.Api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ReadUserDTO registerUser(@Valid CreateUserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new DuplicateResourceException("User", "Email", userDTO.email());
        }
        if(userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new DuplicateResourceException("User", "Email", userDTO.username());
        }
        User user = userRepository.save(new User(userDTO));
        return new ReadUserDTO(user);
    }

    //TODO --> Authorization for reading users
    public ReadUserDTO getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return new ReadUserDTO(userOptional.get());
    }

}
