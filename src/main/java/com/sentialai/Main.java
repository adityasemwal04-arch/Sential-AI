package com.sentialai;

import com.sentialai.model.AccidentReport;
import com.sentialai.model.GPSLocation;
import com.sentialai.model.SensorData;
import com.sentialai.service.AccidentDetector;
import com.sentialai.service.AlertService;
import com.sentialai.service.GPSService;
import com.sentialai.service.SOSService;
import com.sentialai.service.SensorService;

public class Main {

    public static void main(String[] args) {

        SensorService sensorService = new SensorService();
        GPSService gpsService = new GPSService();
        AccidentDetector detector = new AccidentDetector();
        AlertService alertService = new AlertService();
        SOSService sosService = new SOSService();

        System.out.println("===== Sential AI =====");

        SensorData sensorData = sensorService.getSensorData();
        GPSLocation location = gpsService.getCurrentLocation();

        System.out.println(sensorData);

        AccidentReport report =
                detector.detectAccident(sensorData, location);

        if (report != null) {

            alertService.triggerAlarm();
            sosService.sendSOS(report);

        } else {

            System.out.println();
            System.out.println("No accident detected.");
        }
    }
}