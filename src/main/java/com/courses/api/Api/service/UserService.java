package com.courses.api.Api.service;

import com.courses.api.Api.dto.user.CreateUserDTO;
import com.courses.api.Api.dto.user.ReadUserDTO;
import com.courses.api.Api.dto.user.UpdateUserDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.entity.UserRoles;
import com.courses.api.Api.infra.exception.DuplicateResourceException;
import com.courses.api.Api.infra.exception.UserException;
import com.courses.api.Api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReadUserDTO registerUser(@Valid CreateUserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new DuplicateResourceException("User", "Email", userDTO.email());
        }
        if(userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new DuplicateResourceException("User", "Email", userDTO.username());
        }

        User newUser = new User(userDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User user = userRepository.save(newUser);
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

    public void deleteById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        User user = userOptional.get();

        if (user.getUserRole() == UserRoles.ROLE_ADMIN) {
            throw new UserException("This user can't be deleted");
        }

        if (user.getActive()) {
            user.setActive(false);
            userRepository.save(user);
        }

    }

    public Page<ReadUserDTO> getUsers(Pageable pageable) {
        return userRepository.findAllByActiveTrue(pageable).map(ReadUserDTO::new);
    }

    public UpdateUserDTO updateUser(Long id, UpdateUserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        User user = userOptional.get();
        user.updateUser(userDTO);

        //Refactor?
        if (userDTO.password() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User savedUser = userRepository.save(user);
        return new UpdateUserDTO(
                savedUser.getName(),
                savedUser.getUsername(),
                savedUser.getPassword()
        );
    }

}
