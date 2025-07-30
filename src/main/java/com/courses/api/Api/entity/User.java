package com.courses.api.Api.entity;

import com.courses.api.Api.dto.CreateUserDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRoles userRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(CreateUserDTO userDTO) {
        this.name = userDTO.name();
        this.username = userDTO.username();
        this.password = userDTO.password();
        this.email = userDTO.email();
        this.userRole = UserRoles.USER;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
