package com.bixspace.livesculture.data.repository.remote;

import com.bixspace.livesculture.data.PlaceModel;

import java.util.ArrayList;

/**
 * Created by junior on 28/11/16.
 */

public class TrackPlaceModel {

    private String next;
    private String preview;
    private ArrayList<PlaceModel> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public ArrayList<PlaceModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<PlaceModel> results) {
        this.results = results;
    }
}
