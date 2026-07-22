
package com.sentialai.service;

import com.sentialai.model.GPSLocation;

import java.util.Random;

public class GPSService {

    private final Random random = new Random();

    public GPSLocation getCurrentLocation() {

        double latitude = 28.6139 + (random.nextDouble() - 0.5) * 0.02;
        double longitude = 77.2090 + (random.nextDouble() - 0.5) * 0.02;

        return new GPSLocation(latitude, longitude);
    }
}