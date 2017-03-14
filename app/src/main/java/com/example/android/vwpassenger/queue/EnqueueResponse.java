package com.example.android.vwpassenger.queue;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pulitz on 3/12/2017.
 */

public class EnqueueResponse {
    @SerializedName("isSuccess")
    private boolean isSuccess;
    @SerializedName("updatedQueue")
    private List<Queue> queueUpdates;
    @SerializedName("Message")
    private String message;

    public EnqueueResponse(boolean isSuccess, List<Queue> queueUpdates, String message) {
        this.isSuccess = isSuccess;
        this.queueUpdates = queueUpdates;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<Queue> getQueueUpdates() {
        return queueUpdates;
    }

    public void setQueueUpdates(List<Queue> queueUpdates) {
        this.queueUpdates = queueUpdates;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
