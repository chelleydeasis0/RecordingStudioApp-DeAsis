package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.service.PlaylistService;

import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public List<Playlist> handleViewAllPlaylists() {
        return playlistService.getAllPlaylists();
    }
}