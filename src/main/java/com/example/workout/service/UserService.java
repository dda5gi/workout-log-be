package com.example.workout.service;

import com.example.workout.error.exception.EntityNotFoundException;
import com.example.workout.error.exception.PasswordNotMatchedException;
import com.example.workout.model.UserEntity;
import com.example.workout.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        if(userEntity == null || userEntity.getEmail() == null) {
            throw new RuntimeException("Invalid Arguments");
        }
        final String email = userEntity.getEmail();
        if(userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final Optional<UserEntity> originalUser = userRepository.findByEmail(email);
        originalUser.orElseThrow(() -> new EntityNotFoundException());
        if(encoder.matches(password, originalUser.get().getPassword())) {
            return originalUser.get();
        }
        else {
            throw new PasswordNotMatchedException();
        }
    }
}
