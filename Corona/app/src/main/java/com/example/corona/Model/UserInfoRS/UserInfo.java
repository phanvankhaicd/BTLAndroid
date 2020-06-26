package com.example.corona.Model.UserInfoRS;

import com.example.corona.Model.UserInfoRS.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

@SerializedName("data")
@Expose
private Data data;
@SerializedName("errorCode")
@Expose
private Integer errorCode;
@SerializedName("message")
@Expose
private String message;

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public Integer getErrorCode() {
return errorCode;
}

public void setErrorCode(Integer errorCode) {
this.errorCode = errorCode;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}