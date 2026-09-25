package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongRepositoryImpl implements SongRepository {

    private final DbConnection dbConnection;

    public SongRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM songs";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {

                songs.add(new Song(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("length"),
                        result.getString("genre"),
                        result.getInt("album_id")
                ));
            }

        } catch (Exception e) {
            System.err.println("Get All Songs Error: " + e.getMessage());
        }

        return songs;
    }
}