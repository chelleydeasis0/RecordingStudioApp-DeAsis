package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.UserController;
import com.joysistvi.recordingapp.model.User;

import java.util.List;
import java.util.Scanner;

public class AdminDashboardView {

    private final ArtistView artistView;
    private final AlbumView albumView;
    private final SongView songView;
    private final PlaylistView playlistView;
    private final UserController userController;
    private final Scanner scanner;

    public AdminDashboardView(
            ArtistView artistView,
            AlbumView albumView,
            SongView songView,
            PlaylistView playlistView,
            UserController userController,
            Scanner scanner
    ) {

        this.artistView = artistView;
        this.albumView = albumView;
        this.songView = songView;
        this.playlistView = playlistView;
        this.userController = userController;
        this.scanner = scanner;
    }

    public void run() {

        int choice;

        do {

            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. Artist Management");
            System.out.println("2. View Albums");
            System.out.println("3. View Songs");
            System.out.println("4. View Playlists");
            System.out.println("5. View Users");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {

                case 1 -> artistView.run();
                case 2 -> albumView.viewAllAlbums();
                case 3 -> songView.viewAllSongs();
                case 4 -> playlistView.viewAllPlaylists();
                case 5 -> viewUsers();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    private void viewUsers() {
        System.out.println("\n----- Users -----");
        List<User> users = userController.handleViewAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.printf("%-5s | %-20s | %-10s%n", "ID", "Username", "Role");
        System.out.println("-".repeat(42));

        for (User user : users) {

            System.out.printf("%-5d | %-20s | %-10s%n", user.getId(),
                    user.getUsername(),
                    user.getRole());
        }
    }

    private int readInt() {

        while (true) {
            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input.trim());

            } catch (NumberFormatException e) {

                System.out.print("Please enter a valid number: ");
            }
        }
    }
}