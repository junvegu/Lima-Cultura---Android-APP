package com.bixspace.livesculture.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by junior on 28/11/16.
 */

public class PlaceModel implements Serializable {

    private String id;
    private String name;
    private double longitude;
    private double latitude;
    private String file_1;
    private String file_2;
    private String file_3;
    private String description;
    private String address;
    private boolean is_favorite;
    private ArrayList<EventModel> events;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<EventModel> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventModel> events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFile_1() {
        return file_1;
    }

    public void setFile_1(String file_1) {
        this.file_1 = file_1;
    }

    public String getFile_2() {
        return file_2;
    }

    public void setFile_2(String file_2) {
        this.file_2 = file_2;
    }

    public String getFile_3() {
        return file_3;
    }

    public void setFile_3(String file_3) {
        this.file_3 = file_3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}
