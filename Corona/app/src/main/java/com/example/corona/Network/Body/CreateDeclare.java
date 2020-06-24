package com.example.corona.Network.Body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDeclare {
    @SerializedName("healthMonitorSick")
    @Expose
    private Integer healthMonitorSick;
    @SerializedName("healthMonitorCough")
    @Expose
    private Integer healthMonitorCough;
    @SerializedName("healthMonitorSultry")
    @Expose
    private Integer healthMonitorSultry;
    @SerializedName("healthMonitorTired")
    @Expose
    private Integer healthMonitorTired;
    @SerializedName("healthMonitorGood")
    @Expose
    private Integer healthMonitorGood;

    public CreateDeclare() {
    }

    public CreateDeclare(Integer healthMonitorSick, Integer healthMonitorCough, Integer healthMonitorSultry, Integer healthMonitorTired, Integer healthMonitorGood) {
        this.healthMonitorSick = healthMonitorSick;
        this.healthMonitorCough = healthMonitorCough;
        this.healthMonitorSultry = healthMonitorSultry;
        this.healthMonitorTired = healthMonitorTired;
        this.healthMonitorGood = healthMonitorGood;
    }

    public Integer getHealthMonitorSick() {
        return healthMonitorSick;
    }

    public void setHealthMonitorSick(Integer healthMonitorSick) {
        this.healthMonitorSick = healthMonitorSick;
    }

    public Integer getHealthMonitorCough() {
        return healthMonitorCough;
    }

    public void setHealthMonitorCough(Integer healthMonitorCough) {
        this.healthMonitorCough = healthMonitorCough;
    }

    public Integer getHealthMonitorSultry() {
        return healthMonitorSultry;
    }

    public void setHealthMonitorSultry(Integer healthMonitorSultry) {
        this.healthMonitorSultry = healthMonitorSultry;
    }

    public Integer getHealthMonitorTired() {
        return healthMonitorTired;
    }

    public void setHealthMonitorTired(Integer healthMonitorTired) {
        this.healthMonitorTired = healthMonitorTired;
    }

    public Integer getHealthMonitorGood() {
        return healthMonitorGood;
    }

    public void setHealthMonitorGood(Integer healthMonitorGood) {
        this.healthMonitorGood = healthMonitorGood;
    }
}
