package com.example.Product.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Product.jwt.JwtUtil;
import com.example.Product.Entity.UserEntity;
import com.example.Product.Entity.AuthorityEntity;
import com.example.Product.Repository.UserRepository;
import com.example.Product.Repository.AuthorityRepository;
import com.example.Product.Request.UserLoginRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("This user already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPhonenumber(user.getPhonenumber());
        userEntity.setEnabled(true);

        userRepository.save(userEntity);
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setUsername(userEntity.getUsername());
        authorityEntity.setAuthority("ROLE_USER");
        authorityRepository.save(authorityEntity);
    }

    public Map<String, String> login(UserLoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), null);

        return Map.of(
            "token", token,
            "username", user.getUsername(),
            "name", user.getName() != null ? user.getName() : "",
            "surname", user.getSurname() != null ? user.getSurname() : "",
            "email", user.getEmail() != null ? user.getEmail() : ""
        );
    }
    public void logout(String token) {
        jwtUtil.invalidatedToken(token);
        SecurityContextHolder.clearContext();
    }

    public UserEntity getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found :("));
    }
}