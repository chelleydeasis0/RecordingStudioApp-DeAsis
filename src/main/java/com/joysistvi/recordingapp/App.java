package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.cliview.*;
import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.controller.*;
import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DbConnection dbConnection =
                new DbConnection();


        // ARTIST
        ArtistRepo artistRepository = new ArtistRepoImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistView = new ArtistView(artistController, scanner);


        // ALBUM
        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumController albumController = new AlbumController(albumService);
        AlbumView albumView = new AlbumView(albumController);


        // SONG
        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
        SongService songService = new SongServiceImpl(songRepository);
        SongController songController = new SongController(songService);
        SongView songView = new SongView(songController);


        // PLAYLIST
        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
        PlaylistService playlistService = new PlaylistServiceImpl(playlistRepository);
        PlaylistController playlistController = new PlaylistController(playlistService);
        PlaylistView playlistView = new PlaylistView(playlistController);


        // USER
        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserController userController = new UserController(userService);


        // VIEWS
        AuthView authView = new AuthView(userController, scanner);
        AdminDashboardView adminDashboard = new AdminDashboardView(artistView, albumView, songView, playlistView, userController, scanner);
        UserDashboardView userDashboard = new UserDashboardView(artistController, albumController,
                songController, playlistController, scanner);


        // MAIN PROGRAM LOOP

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("      RECORDING STUDIO APP");
            System.out.println("================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                choice = -1;
            }


            switch (choice) {

                case 1 -> {
                    User user = authView.login();

                    if (user != null) {
                        if (user.getRole().equalsIgnoreCase("ADMIN")) {
                            adminDashboard.run();

                        } else {

                            userDashboard.run(user);
                        }
                    }
                }

                case 2 -> authView.register();
                case 0 -> System.out.println("\nThank you for using Recording Studio App!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}