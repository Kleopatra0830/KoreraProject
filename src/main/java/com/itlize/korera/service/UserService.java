package com.itlize.korera.service;

import com.itlize.korera.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserByUsername(String username);
    public boolean checkPassword(User user, String rawPassword);
    Optional<User> getUserByResetToken(String resetToken);
}
