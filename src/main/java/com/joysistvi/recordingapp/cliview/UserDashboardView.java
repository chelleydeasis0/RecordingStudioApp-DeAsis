package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.model.User;

import java.util.List;
import java.util.Scanner;

public class UserDashboardView {

    private final ArtistController artistController;
    private final AlbumController albumController;
    private final SongController songController;
    private final PlaylistController playlistController;
    private final Scanner scanner;

    public UserDashboardView(
            ArtistController artistController,
            AlbumController albumController,
            SongController songController,
            PlaylistController playlistController,
            Scanner scanner
    ) {

        this.artistController = artistController;
        this.albumController = albumController;
        this.songController = songController;
        this.playlistController = playlistController;
        this.scanner = scanner;
    }

    public void run(User user) {

        int choice;

        do {

            System.out.println("\n===== USER DASHBOARD =====");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. View Artists");
            System.out.println("2. View Albums");
            System.out.println("3. View Songs");
            System.out.println("4. View Playlists");
            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = readInt();

            switch (choice) {

                case 1 -> viewArtists();
                case 2 -> viewAlbums();
                case 3 -> viewSongs();
                case 4 -> viewPlaylists();
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    private void viewArtists() {
        System.out.println("\n----- Artists -----");
        List<Artist> artists = artistController.handleViewAllArtists();
        for (Artist artist : artists) {
            System.out.printf("%-5d | %-25s%n",
                    artist.getId(),
                    artist.getName());
        }
    }

    private void viewAlbums() {

        System.out.println("\n----- Albums -----");
        List<Album> albums = albumController.handleViewAllAlbums();

        for (Album album : albums) {
            System.out.printf("%-5d | %-25s | %-6d%n",
                    album.getId(),
                    album.getName(),
                    album.getYear());
        }
    }

    private void viewSongs() {
        System.out.println("\n----- Songs -----");
        List<Song> songs = songController.handleViewAllSongs();

        for (Song song : songs) {
            System.out.printf("%-5d | %-35s | %-10s%n",
                    song.getId(),
                    song.getTitle(),
                    song.getGenre());
        }
    }

    private void viewPlaylists() {

        System.out.println("\n----- Playlists -----");
        List<Playlist> playlists = playlistController.handleViewAllPlaylists();
        for (Playlist playlist : playlists) {
            System.out.printf("%-5d | %-12s | User ID: %-5d%n",
                    playlist.getId(),
                    playlist.getDateCreated(),
                    playlist.getUserId()
            );
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