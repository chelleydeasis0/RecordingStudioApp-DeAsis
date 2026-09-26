package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.User;

import java.util.List;

public interface UserRepository {

    User login(String username, String password);
    boolean registerUser(User user);
    boolean usernameExists(String username);
    List<User> getAllUsers();
}