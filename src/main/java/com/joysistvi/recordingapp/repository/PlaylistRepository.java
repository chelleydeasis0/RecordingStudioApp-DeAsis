package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;

import java.util.List;

public interface PlaylistRepository {

    List<Playlist> getAllPlaylists();
}