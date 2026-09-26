package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final DbConnection dbConnection;
    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);
            prep.setString(2, password);

            try (ResultSet result = prep.executeQuery()) {

                if (result.next()) {

                    return new User(result.getInt("id"),
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("role"));
                }
            }

        } catch (Exception e) {
            System.err.println("Login Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean registerUser(User user) {

        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getRole());

            return prep.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Register User Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean usernameExists(String username) {

        String query = "SELECT id FROM users WHERE username = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);

            try (ResultSet result = prep.executeQuery()) {
                return result.next();
            }

        } catch (Exception e) {
            System.err.println("Check Username Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {

                users.add(new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("role")));
            }

        } catch (Exception e) {
            System.err.println("Get All Users Error: " + e.getMessage());
        }

        return users;
    }
}