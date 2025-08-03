package com.courses.api.Api.repository;

import com.courses.api.Api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Page<User> findAllByActiveTrue(Pageable pageable);
    Optional<User> findByIdAndActiveTrue(UUID id);
}
