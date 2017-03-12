package com.example.android.vwpassenger.queue;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pulitz on 3/12/2017.
 */

public class QueueUpdate {
    @SerializedName("trip_ID")
    private int tripID;
    @SerializedName("count")
    private int count;

    public QueueUpdate(int tripID, int count) {
        this.tripID = tripID;
        this.count = count;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
