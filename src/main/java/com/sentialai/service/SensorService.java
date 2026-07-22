package com.sentialai.service;

import com.sentialai.model.SensorData;

import java.util.Random;

public class SensorService {

    private final Random random = new Random();

    public SensorData getSensorData() {

        double acceleration = 2 + random.nextDouble() * 10;
        double gyroscope = random.nextDouble() * 5;
        boolean helmetWorn = random.nextBoolean();

        return new SensorData(acceleration, gyroscope, helmetWorn);
    }
}