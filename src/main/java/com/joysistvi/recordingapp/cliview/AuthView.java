package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;

import java.util.Scanner;

public class AuthView {

    private final UserController userController;
    private final Scanner scanner;

    public AuthView(
            UserController userController,
            Scanner scanner
    ) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public User login() {

        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user =
                userController.handleLogin(username, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return null;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + user.getUsername() + "!");
        return user;
    }

    public void register() {

        System.out.println("\n===== REGISTER =====");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user =
                new User(username, password, "USER");
        boolean isSuccess = userController.handleRegister(user);

        if (isSuccess) {
            System.out.println("Account created successfully.");

        } else {
            System.out.println("Registration failed. Username may already exist."
            );
        }
    }
}