package com.example.corona.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("accountId")
@Expose
private Integer accountId;
@SerializedName("accountType")
@Expose
private Integer accountType;
@SerializedName("accountName")
@Expose
private String accountName;
@SerializedName("token")
@Expose
private String token;
@SerializedName("gender")
@Expose
private Integer gender;
@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("fullName")
@Expose
private String fullName;

public Integer getAccountId() {
return accountId;
}

public void setAccountId(Integer accountId) {
this.accountId = accountId;
}

public Integer getAccountType() {
return accountType;
}

public void setAccountType(Integer accountType) {
this.accountType = accountType;
}

public String getAccountName() {
return accountName;
}

public void setAccountName(String accountName) {
this.accountName = accountName;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public Integer getGender() {
return gender;
}

public void setGender(Integer gender) {
this.gender = gender;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

}