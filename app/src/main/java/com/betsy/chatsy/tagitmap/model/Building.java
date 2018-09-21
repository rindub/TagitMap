package com.betsy.chatsy.tagitmap.model;

/**
 * Created by RinoDuboković on 21.09.2018..
 */

public class Building {

    private String indoorMapId;
    private double latitude;
    private double longitude;

    public String getIndoorMapId() {
        return indoorMapId;
    }

    public void setIndoorMapId(String indoorMapId) {
        this.indoorMapId = indoorMapId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
