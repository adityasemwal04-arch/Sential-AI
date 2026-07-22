package com.sentialai.model;

import java.time.LocalDateTime;

public class AccidentReport {

    private LocalDateTime accidentTime;
    private GPSLocation location;
    private String severity;

    public AccidentReport(LocalDateTime accidentTime,
                          GPSLocation location,
                          String severity) {

        this.accidentTime = accidentTime;
        this.location = location;
        this.severity = severity;
    }

    public LocalDateTime getAccidentTime() {
        return accidentTime;
    }

    public GPSLocation getLocation() {
        return location;
    }

    public String getSeverity() {
        return severity;
    }

    @Override
    public String toString() {

        return "\n===== Accident Report =====" +
                "\nTime : " + accidentTime +
                "\nLocation : " + location +
                "\nSeverity : " + severity;
    }
}