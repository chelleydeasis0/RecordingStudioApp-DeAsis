package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Song;

import java.util.List;

public interface SongRepository {

    List<Song> getAllSongs();
}