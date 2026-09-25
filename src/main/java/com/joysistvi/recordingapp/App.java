package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.cliview.ArtistView;
import com.joysistvi.recordingapp.cliview.AlbumView;
import com.joysistvi.recordingapp.cliview.SongView;
import com.joysistvi.recordingapp.cliview.PlaylistView;

import com.joysistvi.recordingapp.config.DbConnection;

import com.joysistvi.recordingapp.controller.ArtistController;
import com.joysistvi.recordingapp.controller.AlbumController;
import com.joysistvi.recordingapp.controller.SongController;
import com.joysistvi.recordingapp.controller.PlaylistController;

import com.joysistvi.recordingapp.repository.ArtistRepo;
import com.joysistvi.recordingapp.repository.ArtistRepoImpl;
import com.joysistvi.recordingapp.repository.AlbumRepository;
import com.joysistvi.recordingapp.repository.AlbumRepositoryImpl;
import com.joysistvi.recordingapp.repository.SongRepository;
import com.joysistvi.recordingapp.repository.SongRepositoryImpl;
import com.joysistvi.recordingapp.repository.PlaylistRepository;
import com.joysistvi.recordingapp.repository.PlaylistRepositoryImpl;

import com.joysistvi.recordingapp.service.ArtistService;
import com.joysistvi.recordingapp.service.ArtistServiceImpl;
import com.joysistvi.recordingapp.service.AlbumService;
import com.joysistvi.recordingapp.service.AlbumServiceImpl;
import com.joysistvi.recordingapp.service.SongService;
import com.joysistvi.recordingapp.service.SongServiceImpl;
import com.joysistvi.recordingapp.service.PlaylistService;
import com.joysistvi.recordingapp.service.PlaylistServiceImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DbConnection dbConnection = new DbConnection();


        // ----- Artist -----
        ArtistRepo artistRepository =
                new ArtistRepoImpl(dbConnection);

        ArtistService artistService =
                new ArtistServiceImpl(artistRepository);

        ArtistController artistController =
                new ArtistController(artistService);

        ArtistView artistView =
                new ArtistView(artistController, scanner);


        // ----- Album -----
        AlbumRepository albumRepository =
                new AlbumRepositoryImpl(dbConnection);

        AlbumService albumService =
                new AlbumServiceImpl(albumRepository);

        AlbumController albumController =
                new AlbumController(albumService);

        AlbumView albumView =
                new AlbumView(albumController);


        // ----- Song -----
        SongRepository songRepository =
                new SongRepositoryImpl(dbConnection);

        SongService songService =
                new SongServiceImpl(songRepository);

        SongController songController =
                new SongController(songService);

        SongView songView =
                new SongView(songController);


        // ----- Playlist -----
        PlaylistRepository playlistRepository =
                new PlaylistRepositoryImpl(dbConnection);

        PlaylistService playlistService =
                new PlaylistServiceImpl(playlistRepository);

        PlaylistController playlistController =
                new PlaylistController(playlistService);

        PlaylistView playlistView =
                new PlaylistView(playlistController);


        // ----- Run -----
        artistView.run();

        albumView.viewAllAlbums();

        songView.viewAllSongs();

        playlistView.viewAllPlaylists();


        scanner.close();
    }
}