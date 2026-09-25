package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Album;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository {

    private final DbConnection dbConnection;

    public AlbumRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Album> getAllAlbums() {

        List<Album> albums = new ArrayList<>();
        String query = "SELECT * FROM albums";

        try (Connection conn = dbConnection.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {

            while (result.next()) {

                albums.add(new Album(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("year"),
                        result.getInt("artists_id")
                ));
            }

        } catch (Exception e) {
            System.err.println("Get All Albums Error: " + e.getMessage());
        }

        return albums;
    }
}