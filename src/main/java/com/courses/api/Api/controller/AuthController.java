package com.courses.api.Api.controller;

import com.courses.api.Api.dto.LoginUserDTO;
import com.courses.api.Api.dto.TokenDTO;
import com.courses.api.Api.entity.User;
import com.courses.api.Api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> loginUser(@RequestBody @Valid LoginUserDTO login) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        Authentication authenticate = authenticationManager.authenticate(token);

        String tokenJWT = tokenService.generateToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

}
