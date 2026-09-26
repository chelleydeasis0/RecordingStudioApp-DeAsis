package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public boolean registerUser(User user) {

        if (userRepository.usernameExists(user.getUsername())) {
            return false;
        }

        return userRepository.registerUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}