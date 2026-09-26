package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    boolean registerUser(User user);
    List<User> getAllUsers();
}