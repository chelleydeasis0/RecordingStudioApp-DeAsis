package com.joysistvi.recordingapp.cliview;

import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;

public class SongView {

    private final SongController songController;
    public SongView(SongController songController) {
        this.songController = songController;
    }
    public void viewAllSongs() {

        System.out.println("\n----- View All Songs -----");
        List<Song> songs = songController.handleViewAllSongs();

        if (songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }

        for (Song song : songs) {
            System.out.printf("%-4d | %-35s | %-6s | %-15s | Album ID: %-4d%n",
                    song.getId(),
                    song.getTitle(),
                    song.getLength(),
                    song.getGenre(),
                    song.getAlbumId()
            );
        }
    }
}