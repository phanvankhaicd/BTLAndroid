package com.example.corona.Model.MapNcovi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("name")
@Expose
private String name;
@SerializedName("address")
@Expose
private String address;
@SerializedName("lat")
@Expose
private Double lat;
@SerializedName("lng")
@Expose
private Double lng;
@SerializedName("patientGroup")
@Expose
private String patientGroup;
@SerializedName("note")
@Expose
private String note;
@SerializedName("verifyDate")
@Expose
private String verifyDate;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public Double getLat() {
return lat;
}

public void setLat(Double lat) {
this.lat = lat;
}

public Double getLng() {
return lng;
}

public void setLng(Double lng) {
this.lng = lng;
}

public String getPatientGroup() {
return patientGroup;
}

public void setPatientGroup(String patientGroup) {
this.patientGroup = patientGroup;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public String getVerifyDate() {
return verifyDate;
}

public void setVerifyDate(String verifyDate) {
this.verifyDate = verifyDate;
}

}