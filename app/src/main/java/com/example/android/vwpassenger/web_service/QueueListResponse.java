package com.example.android.vwpassenger.web_service;

import com.example.android.vwpassenger.queue.Queue;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pulitz on 3/13/2017.
 */

public class QueueListResponse {
    @SerializedName("QueueList")
    private List<Queue> queueList = new ArrayList<>();
    @SerializedName("UserTripID")
    private int userTripID;

    public QueueListResponse(List<Queue> queueList, int userTripID) {
        this.queueList = queueList;
        this.userTripID = userTripID;
    }

    public List<Queue> getQueueList() {
        return queueList;
    }

    public void setQueueList(List<Queue> queueList) {
        this.queueList = queueList;
    }

    public int getUserTripID() {
        return userTripID;
    }

    public void setUserTripID(int userTripID) {
        this.userTripID = userTripID;
    }
}
