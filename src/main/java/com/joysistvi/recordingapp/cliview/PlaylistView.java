package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.PlaylistController;
import com.joysistvi.recordingapp.model.Playlist;

import java.util.List;

public class PlaylistView {

    private final PlaylistController playlistController;
    public PlaylistView(PlaylistController playlistController) {
        this.playlistController = playlistController;
    }
    public void viewAllPlaylists() {

        System.out.println("\n----- View All Playlists -----");

        List<Playlist> playlists =
                playlistController.handleViewAllPlaylists();

        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        for (Playlist playlist : playlists) {

            System.out.println(
                    playlist.getId() + " | " +
                            playlist.getDateCreated() + " | User ID: " +
                            playlist.getUserId()
            );
        }
    }
}