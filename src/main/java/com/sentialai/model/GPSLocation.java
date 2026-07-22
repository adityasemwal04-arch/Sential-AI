package com.sentialai.model;

public class GPSLocation {

    private double latitude;
    private double longitude;

    public GPSLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}