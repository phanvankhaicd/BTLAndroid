package com.example.corona.Model.HealthMonitor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("userEmail")
@Expose
private String userEmail;
@SerializedName("userFullname")
@Expose
private String userFullname;
@SerializedName("userBirthday")
@Expose
private String userBirthday;
@SerializedName("userAddress")
@Expose
private String userAddress;
@SerializedName("userPhoneNo")
@Expose
private String userPhoneNo;
@SerializedName("userCmt")
@Expose
private String userCmt;
@SerializedName("userBhxh")
@Expose
private String userBhxh;
@SerializedName("userGender")
@Expose
private Integer userGender;

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getUserEmail() {
return userEmail;
}

public void setUserEmail(String userEmail) {
this.userEmail = userEmail;
}

public String getUserFullname() {
return userFullname;
}

public void setUserFullname(String userFullname) {
this.userFullname = userFullname;
}

public String getUserBirthday() {
return userBirthday;
}

public void setUserBirthday(String userBirthday) {
this.userBirthday = userBirthday;
}

public String getUserAddress() {
return userAddress;
}

public void setUserAddress(String userAddress) {
this.userAddress = userAddress;
}

public String getUserPhoneNo() {
return userPhoneNo;
}

public void setUserPhoneNo(String userPhoneNo) {
this.userPhoneNo = userPhoneNo;
}

public String getUserCmt() {
return userCmt;
}

public void setUserCmt(String userCmt) {
this.userCmt = userCmt;
}

public String getUserBhxh() {
return userBhxh;
}

public void setUserBhxh(String userBhxh) {
this.userBhxh = userBhxh;
}

public Integer getUserGender() {
return userGender;
}

public void setUserGender(Integer userGender) {
this.userGender = userGender;
}

}