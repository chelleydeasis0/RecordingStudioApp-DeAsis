package com.joysistvi.recordingapp.dao;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl implements ArtistRepo {

    private final DbConnection dbConnection;

    // Constructor Injection
    public ArtistDaoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    // READ ALL
    @Override
    public List<Artist> getAllArtists() {

        List<Artist> artists = new ArrayList<>();

        String query =
                "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            ResultSet result = prep.executeQuery();

            while (result.next()) {

                artists.add(new Artist(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Get All Artists: " + e.getMessage());
        }

        return artists;
    }


    // READ BY ID
    @Override
    public Artist readArtistById(int id) {

        String query =
                "SELECT * FROM artists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();

            if (result.next()) {

                return new Artist(
                        result.getInt("id"),
                        result.getString("name")
                );
            }

        } catch (SQLException e) {
            System.err.println("Read Artist: " + e.getMessage());
        }

        return null;
    }


    // SEARCH KEYWORD
    @Override
    public List<Artist> searchArtist(String keyword) {

        List<Artist> artists = new ArrayList<>();

        String query =
                "SELECT * FROM artists " +
                        "WHERE name LIKE ? AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            ResultSet result = prep.executeQuery();

            while (result.next()) {

                artists.add(new Artist(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Search Artist: " + e.getMessage());
        }

        return artists;
    }


    // CREATE
    @Override
    public boolean createArtist(String name) {

        String query =
                "INSERT INTO artists (name) VALUES (?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Create Artist: " + e.getMessage());
            return false;
        }
    }


    // UPDATE
    @Override
    public boolean updateArtist(String name, int id) {

        String query =
                "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Update Artist: " + e.getMessage());
            return false;
        }
    }


    // ARCHIVE
    @Override
    public boolean archiveArtist(int id) {

        String query =
                "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Archive Artist: " + e.getMessage());
            return false;
        }
    }


    // RESTORE
    @Override
    public boolean restoreArtist(int id) {

        String query =
                "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Restore Artist: " + e.getMessage());
            return false;
        }
    }


    // DELETE
    @Override
    public boolean deleteArtist(int id) {

        String query =
                "DELETE FROM artists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Delete Artist: " + e.getMessage());
            return false;
        }
    }


    // SEARCH EXACT NAME
    @Override
    public List<Artist> searchArtistByName(String name) {

        List<Artist> artists = new ArrayList<>();

        String query =
                "SELECT * FROM artists " +
                        "WHERE name = ? AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, name);

            ResultSet result = prep.executeQuery();

            while (result.next()) {

                artists.add(new Artist(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.err.println(
                    "Search Artist By Name: " + e.getMessage()
            );
        }

        return artists;
    }
}