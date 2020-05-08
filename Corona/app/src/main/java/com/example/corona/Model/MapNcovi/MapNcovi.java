package com.example.corona.Model.MapNcovi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapNcovi {

@SerializedName("code")
@Expose
private String code;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<Data> data = null;
@SerializedName("length")
@Expose
private Integer length;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public List<Data> getData() {
return data;
}

public void setData(List<Data> data) {
this.data = data;
}

public Integer getLength() {
return length;
}

public void setLength(Integer length) {
this.length = length;
}

}