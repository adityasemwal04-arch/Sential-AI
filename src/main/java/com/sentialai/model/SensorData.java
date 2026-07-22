package com.sentialai.model;

public class SensorData {

    private double acceleration;
    private double gyroscope;
    private boolean helmetWorn;

    public SensorData(double acceleration, double gyroscope, boolean helmetWorn) {
        this.acceleration = acceleration;
        this.gyroscope = gyroscope;
        this.helmetWorn = helmetWorn;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(double gyroscope) {
        this.gyroscope = gyroscope;
    }

    public boolean isHelmetWorn() {
        return helmetWorn;
    }

    public void setHelmetWorn(boolean helmetWorn) {
        this.helmetWorn = helmetWorn;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "acceleration=" + acceleration +
                ", gyroscope=" + gyroscope +
                ", helmetWorn=" + helmetWorn +
                '}';
    }
}