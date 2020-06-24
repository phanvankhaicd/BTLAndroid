package com.example.corona.Model.HealthMonitor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDeclare {

@SerializedName("healthMonitorId")
@Expose
private Integer healthMonitorId;
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
@SerializedName("healthMonitorStatus")
@Expose
private Integer healthMonitorStatus;
@SerializedName("healthMonitorDescription")
@Expose
private String healthMonitorDescription;
@SerializedName("healthMonitorCreateAt")
@Expose
private String healthMonitorCreateAt;

public Integer getHealthMonitorId() {
return healthMonitorId;
}

public void setHealthMonitorId(Integer healthMonitorId) {
this.healthMonitorId = healthMonitorId;
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

public Integer getHealthMonitorStatus() {
return healthMonitorStatus;
}

public void setHealthMonitorStatus(Integer healthMonitorStatus) {
this.healthMonitorStatus = healthMonitorStatus;
}

public String getHealthMonitorDescription() {
return healthMonitorDescription;
}

public void setHealthMonitorDescription(String healthMonitorDescription) {
this.healthMonitorDescription = healthMonitorDescription;
}

public String getHealthMonitorCreateAt() {
return healthMonitorCreateAt;
}

public void setHealthMonitorCreateAt(String healthMonitorCreateAt) {
this.healthMonitorCreateAt = healthMonitorCreateAt;
}

}