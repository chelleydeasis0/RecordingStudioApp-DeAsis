package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final DbConnection dbConnection;

    public PlaylistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM playlists";

        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {

                playlists.add(new Playlist(
                        result.getInt("id"),
                        result.getDate("date_created"),
                        result.getInt("user_id")
                ));
            }

        } catch (Exception e) {
            System.err.println("Get All Playlists Error: " + e.getMessage());
        }

        return playlists;
    }
}