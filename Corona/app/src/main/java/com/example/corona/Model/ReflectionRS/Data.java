package com.example.corona.Model.ReflectionRS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("reflectionId")
@Expose
private Integer reflectionId;
@SerializedName("reflectionInfoQuestion1")
@Expose
private Integer reflectionInfoQuestion1;
@SerializedName("reflectionInfoQuestion2")
@Expose
private Integer reflectionInfoQuestion2;
@SerializedName("reflectionInfoQuestion3")
@Expose
private Integer reflectionInfoQuestion3;
@SerializedName("reflectionInfoDescription")
@Expose
private String reflectionInfoDescription;
@SerializedName("reflectionInfoTime")
@Expose
private String reflectionInfoTime;
@SerializedName("reflectionCreatedAt")
@Expose
private String reflectionCreatedAt;
@SerializedName("reflectionType")
@Expose
private Integer reflectionType;
@SerializedName("user")
@Expose
private User user;

public Integer getReflectionId() {
return reflectionId;
}

public void setReflectionId(Integer reflectionId) {
this.reflectionId = reflectionId;
}

public Integer getReflectionInfoQuestion1() {
return reflectionInfoQuestion1;
}

public void setReflectionInfoQuestion1(Integer reflectionInfoQuestion1) {
this.reflectionInfoQuestion1 = reflectionInfoQuestion1;
}

public Integer getReflectionInfoQuestion2() {
return reflectionInfoQuestion2;
}

public void setReflectionInfoQuestion2(Integer reflectionInfoQuestion2) {
this.reflectionInfoQuestion2 = reflectionInfoQuestion2;
}

public Integer getReflectionInfoQuestion3() {
return reflectionInfoQuestion3;
}

public void setReflectionInfoQuestion3(Integer reflectionInfoQuestion3) {
this.reflectionInfoQuestion3 = reflectionInfoQuestion3;
}

public String getReflectionInfoDescription() {
return reflectionInfoDescription;
}

public void setReflectionInfoDescription(String reflectionInfoDescription) {
this.reflectionInfoDescription = reflectionInfoDescription;
}

public String getReflectionInfoTime() {
return reflectionInfoTime;
}

public void setReflectionInfoTime(String reflectionInfoTime) {
this.reflectionInfoTime = reflectionInfoTime;
}

public String getReflectionCreatedAt() {
return reflectionCreatedAt;
}

public void setReflectionCreatedAt(String reflectionCreatedAt) {
this.reflectionCreatedAt = reflectionCreatedAt;
}

public Integer getReflectionType() {
return reflectionType;
}

public void setReflectionType(Integer reflectionType) {
this.reflectionType = reflectionType;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

}