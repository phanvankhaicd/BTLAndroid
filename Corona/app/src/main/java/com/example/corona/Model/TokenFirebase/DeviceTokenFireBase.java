package com.example.corona.Model.TokenFirebase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceTokenFireBase {

@SerializedName("data")
@Expose
private Object data;
@SerializedName("errorCode")
@Expose
private Integer errorCode;
@SerializedName("message")
@Expose
private String message;

public Object getData() {
return data;
}

public void setData(Object data) {
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