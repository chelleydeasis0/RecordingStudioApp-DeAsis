package com.joysistvi.recordingapp.model;

import java.sql.Date;

public class Playlist {

    private int id;
    private Date dateCreated;
    private int userId;

    public Playlist(int id, Date dateCreated, int userId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getUserId() {
        return userId;
    }
}