package com.example.Product.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Product.Request.UserLoginRequest;
import com.example.Product.Service.UserService;
import com.example.Product.jwt.SecurityUtil;
import com.example.Product.Entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid UserEntity userEntity) {
        userService.register(userEntity);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest userLoginRequest) {
        Map<String, String> response = userService.login(userLoginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid Authorization header");
        }
        String token = header.substring(7);
        userService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/me")
    public UserEntity me() {
        String username = SecurityUtil.getCurrentUsername();
        return userService.getUserInfo(username);
    }
}