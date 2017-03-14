package com.example.android.vwpassenger.queue;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/4/2017.
 */

public class Queue implements Comparable<Queue> {
    @SerializedName("TripID")
    private int tripID;
    @SerializedName("OriginName")
    private String originName;
    @SerializedName("DestinationName")
    private String destinationName;
    @SerializedName("NumberInQueue")
    private int numberInQueue;
//    private String beaconUUID;
//    private int beaconMajor;
//    private int beaconMinor;


    public Queue(int tripID, String originName, String destinationName, int numberInQueue) {
        this.tripID = tripID;
        this.originName = originName;
        this.destinationName = destinationName;
        this.numberInQueue = numberInQueue;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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

    @Override
    public int compareTo(@NonNull Queue queue) {
        return Integer.valueOf(tripID).compareTo(queue.tripID);
    }
}
