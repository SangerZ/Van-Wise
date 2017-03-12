package com.example.android.vwpassenger.queue;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pulitz on 3/12/2017.
 */

public class EnqueueResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("allQueue")
    private List<QueueUpdate> queueUpdates;

    @SerializedName("User_Trip")
    private int inQueueID;


}
