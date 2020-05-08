package com.example.corona.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("username")
@Expose
private String username;
@SerializedName("email")
@Expose
private String email;
@SerializedName("fullName")
@Expose
private String fullName;
@SerializedName("birthday")
@Expose
private String birthday;
@SerializedName("address")
@Expose
private String address;
@SerializedName("phoneNo")
@Expose
private String phoneNo;
@SerializedName("cmt")
@Expose
private String cmt;
@SerializedName("bhxh")
@Expose
private String bhxh;
@SerializedName("gender")
@Expose
private String gender;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getBirthday() {
return birthday;
}

public void setBirthday(String birthday) {
this.birthday = birthday;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getPhoneNo() {
return phoneNo;
}

public void setPhoneNo(String phoneNo) {
this.phoneNo = phoneNo;
}

public String getCmt() {
return cmt;
}

public void setCmt(String cmt) {
this.cmt = cmt;
}

public String getBhxh() {
return bhxh;
}

public void setBhxh(String bhxh) {
this.bhxh = bhxh;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

}