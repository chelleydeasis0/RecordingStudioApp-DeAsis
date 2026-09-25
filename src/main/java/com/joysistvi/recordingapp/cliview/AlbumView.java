package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.model.Album;

import java.util.List;

public class AlbumView {

    private final AlbumController albumController;

    public AlbumView(AlbumController albumController) {
        this.albumController = albumController;
    }

    public void run() {

        System.out.println("\n----- Album Management -----");

        List<Album> albums = albumController.handleViewAllAlbums();

        if (albums.isEmpty()) {
            System.out.println("No albums found.");
            return;
        }

        for (Album album : albums) {
            System.out.printf(
                    "%-4d | %-25s | %-6d | Artist ID: %-4d%n",
                    album.getId(),
                    album.getName(),
                    album.getYear(),
                    album.getArtistId()
            );
        }
    }

    public void viewAllAlbums() {
        run();
    }
}