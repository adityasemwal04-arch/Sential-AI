package com.sentialai.service;

import com.sentialai.model.AccidentReport;
import com.sentialai.model.GPSLocation;
import com.sentialai.model.SensorData;
import com.sentialai.util.Constants;

import java.time.LocalDateTime;

public class AccidentDetector {

    public AccidentReport detectAccident(SensorData data,
                                         GPSLocation location) {

        if (data.getAcceleration() >= Constants.IMPACT_THRESHOLD) {

            String severity;

            if (data.getAcceleration() >= 12) {
                severity = "HIGH";
            } else if (data.getAcceleration() >= 10) {
                severity = "MEDIUM";
            } else {
                severity = "LOW";
            }

            return new AccidentReport(
                    LocalDateTime.now(),
                    location,
                    severity
            );
        }

        return null;
    }
}