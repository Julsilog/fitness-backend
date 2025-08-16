package com.fitnessapp.controller;

import com.fitnessapp.model.User;
import com.fitnessapp.dto.UserDTO;
import com.fitnessapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    // GET /users - Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /users/{id} - Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // PUT /users/{id} - Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable final Long id, @RequestBody final User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    // DELETE /users/{id} - Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    // POST /users - Create a user with validation
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody final UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
