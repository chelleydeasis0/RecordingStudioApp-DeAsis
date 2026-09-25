package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.repository.AlbumRepository;

import java.util.List;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepo;

    public AlbumServiceImpl(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepo.getAllAlbums();
    }
}