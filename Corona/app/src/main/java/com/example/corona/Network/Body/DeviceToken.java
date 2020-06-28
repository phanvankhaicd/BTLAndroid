package com.example.corona.Network.Body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceToken {

    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;

    public DeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

}