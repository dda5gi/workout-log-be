package com.example.workout.controller;

import com.example.workout.dto.ResponseDTO;
import com.example.workout.dto.UserDTO;
import com.example.workout.model.UserEntity;
import com.example.workout.security.TokenProvider;
import com.example.workout.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        UserEntity user = UserEntity.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        UserEntity registeredUser = userService.create(user);
        UserDTO responseUserDTO = UserDTO.builder()
                .email(registeredUser.getEmail())
                .id(registeredUser.getId())
                .username(registeredUser.getUsername())
                .build();
        return ResponseEntity.ok().body(responseUserDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getEmail(),
                userDTO.getPassword(),
                passwordEncoder
        );

        final String token = tokenProvider.create(user);
        final UserDTO responseUserDTO = UserDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .token(token)
                .build();
        return ResponseEntity.ok().body(responseUserDTO);
    }
}
