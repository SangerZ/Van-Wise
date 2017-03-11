package com.example.android.vwpassenger.queue;

/**
 * Created by Pulitz on 3/4/2017.
 */

public class Trip {
    private int tripID;
    private int icon;
    private String destinationName;
    private int numberInQueue;
    private boolean selected;
    private String beaconUUID;
    private int beaconMajor;
    private int beaconMinor;

    public Trip(int tripID, int icon, String destinationName, int numberInQueue, boolean selected, String beaconUUID, int beaconMajor, int beaconMinor) {
        this.tripID = tripID;
        this.icon = icon;
        this.destinationName = destinationName;
        this.numberInQueue = numberInQueue;
        this.selected = selected;
        this.beaconUUID = beaconUUID;
        this.beaconMajor = beaconMajor;
        this.beaconMinor = beaconMinor;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getBeaconUUID() {
        return beaconUUID;
    }

    public void setBeaconUUID(String beaconUUID) {
        this.beaconUUID = beaconUUID;
    }

    public int getBeaconMajor() {
        return beaconMajor;
    }

    public void setBeaconMajor(int beaconMajor) {
        this.beaconMajor = beaconMajor;
    }

    public int getBeaconMinor() {
        return beaconMinor;
    }

    public void setBeaconMinor(int beaconMinor) {
        this.beaconMinor = beaconMinor;
    }
}
