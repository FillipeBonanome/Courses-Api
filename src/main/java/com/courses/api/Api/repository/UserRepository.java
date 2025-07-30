package com.courses.api.Api.repository;

import com.courses.api.Api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
