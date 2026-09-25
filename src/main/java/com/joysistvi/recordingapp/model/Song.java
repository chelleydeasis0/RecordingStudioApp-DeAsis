package com.joysistvi.recordingapp.model;

public class Song {

    private int id;
    private String title;
    private String length;
    private String genre;
    private int albumId;

    public Song(int id, String title, String length, String genre, int albumId) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public String getGenre() {
        return genre;
    }

    public int getAlbumId() {
        return albumId;
    }
}