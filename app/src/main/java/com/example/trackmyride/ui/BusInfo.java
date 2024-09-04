package com.example.trackmyride.ui;

public class BusInfo {
    private String BusLatitude, BusNumber, BusLongitude, BusDocID, BusCurrentUserID, BusETA, BusStart, BusStop;

    public BusInfo(String busLatitude, String busNumber, String busLongitude, String busDocID, String busCurrentUserID, String eta, String busStart, String busStop) {
        BusLatitude = busLatitude;
        BusNumber = busNumber;
        BusLongitude = busLongitude;
        BusDocID = busDocID;
        BusCurrentUserID = busCurrentUserID;
        BusETA = eta;
        BusStart = busStart;
        BusStop = busStop;
    }

    public String getBusStart() {
        return BusStart;
    }

    public void setBusStart(String busStart) {
        BusStart = busStart;
    }

    public String getBusStop() {
        return BusStop;
    }

    public void setBusStop(String busStop) {
        BusStop = busStop;
    }

    public String getBusETA() {
        return BusETA;
    }

    public void setBusETA(String busETA) {
        BusETA = busETA;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        BusNumber = busNumber;
    }

    public String getBusLongitude() {
        return BusLongitude;
    }

    public void setBusLongitude(String busLongitude) {
        BusLongitude = busLongitude;
    }

    public String getBusLatitude() {
        return BusLatitude;
    }

    public void setBusLatitude(String busLatitude) {
        BusLatitude = busLatitude;
    }

    public String getBusDocID() {
        return BusDocID;
    }

    public void setBusDocID(String busDocID) {
        BusDocID = busDocID;
    }

    public String getBusCurrentUserID() {
        return BusCurrentUserID;
    }

    public void setBusCurrentUserID(String busCurrentUserID) {
        BusCurrentUserID = busCurrentUserID;
    }
}
