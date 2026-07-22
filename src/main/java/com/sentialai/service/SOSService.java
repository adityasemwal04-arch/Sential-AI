package com.sentialai.service;

import com.sentialai.model.AccidentReport;
import com.sentialai.util.Constants;

public class SOSService {

    public void sendSOS(AccidentReport report) {

        System.out.println();
        System.out.println("Sending SOS...");
        System.out.println("Emergency Contact : " + Constants.EMERGENCY_CONTACT);
        System.out.println(report);
        System.out.println("Google Maps:");
        System.out.println(
                "https://maps.google.com/?q="
                        + report.getLocation().getLatitude()
                        + ","
                        + report.getLocation().getLongitude()
        );
    }
}