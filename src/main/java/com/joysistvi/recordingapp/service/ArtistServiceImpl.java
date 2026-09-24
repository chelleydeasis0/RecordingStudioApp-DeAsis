package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepo;

import java.util.List;

public class ArtistServiceImpl {

    private final ArtistRepo artistrepo;
    public ArtistServiceImpl(ArtistRepo artistrepo) {
        this.artistrepo = artistrepo;

    }
    @Override
    public List<Artist> getAllArtists() {
        return artistRepo.getAllArtists();
    }

    }


}
