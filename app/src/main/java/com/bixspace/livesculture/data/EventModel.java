package com.bixspace.livesculture.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by junior on 27/11/16.
 */

public class EventModel implements Serializable {
    private String id;
    private String name;
    private String start_hour;
    private String end_hour;
    private float min_price;
    private String image_1;
    private String image_category;
    private boolean is_qualified;
    private float score;


     public ArrayList<EventModel> getModels(){

        ArrayList<EventModel> eventModels = new ArrayList<>();
        eventModels.add(new EventModel());
        eventModels.add(new EventModel());
        eventModels.add(new EventModel());
        eventModels.add(new EventModel());

        return eventModels;
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

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public float getMin_price() {
        return min_price;
    }

    public void setMin_price(float min_price) {
        this.min_price = min_price;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_category() {
        return image_category;
    }

    public void setImage_category(String image_category) {
        this.image_category = image_category;
    }

    public boolean is_qualified() {
        return is_qualified;
    }

    public void setIs_qualified(boolean is_qualified) {
        this.is_qualified = is_qualified;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
