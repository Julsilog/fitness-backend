package com.fitnessapp.service;

import com.fitnessapp.ConstantsUtil;
import com.fitnessapp.dto.UserDTO;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(final Long id, final User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    public String deleteUser(@PathVariable final Long id) {
        userRepository.deleteById(id);
        return "User with ID " + id + " has been deleted.";
    }

    public String createUser(@Valid @RequestBody final UserDTO userDTO) {
        // Convert DTO → Entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        // Save to DB
        userRepository.save(user);

        return ConstantsUtil.USER_CREATED_SUCCESSFULLY;
    }
}
