package com.joysistvi.recordingapp.controller;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    public List<Song> handleViewAllSongs() {
        return songService.getAllSongs();
    }
}