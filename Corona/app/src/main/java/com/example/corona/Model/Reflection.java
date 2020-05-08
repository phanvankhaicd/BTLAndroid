package com.example.corona.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reflection {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("questionInfo1")
@Expose
private String questionInfo1;
@SerializedName("questionInfo2")
@Expose
private String questionInfo2;
@SerializedName("questionInfo3")
@Expose
private String questionInfo3;
@SerializedName("questionContact1")
@Expose
private String questionContact1;
@SerializedName("questionContact2")
@Expose
private String questionContact2;
@SerializedName("questionContact3")
@Expose
private String questionContact3;
@SerializedName("reflectionType")
@Expose
private String reflectionType;
@SerializedName("description")
@Expose
private String description;
@SerializedName("time")
@Expose
private String time;
@SerializedName("address")
@Expose
private String address;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getQuestionInfo1() {
return questionInfo1;
}

public void setQuestionInfo1(String questionInfo1) {
this.questionInfo1 = questionInfo1;
}

public String getQuestionInfo2() {
return questionInfo2;
}

public void setQuestionInfo2(String questionInfo2) {
this.questionInfo2 = questionInfo2;
}

public String getQuestionInfo3() {
return questionInfo3;
}

public void setQuestionInfo3(String questionInfo3) {
this.questionInfo3 = questionInfo3;
}

public String getQuestionContact1() {
return questionContact1;
}

public void setQuestionContact1(String questionContact1) {
this.questionContact1 = questionContact1;
}

public String getQuestionContact2() {
return questionContact2;
}

public void setQuestionContact2(String questionContact2) {
this.questionContact2 = questionContact2;
}

public String getQuestionContact3() {
return questionContact3;
}

public void setQuestionContact3(String questionContact3) {
this.questionContact3 = questionContact3;
}

public String getReflectionType() {
return reflectionType;
}

public void setReflectionType(String reflectionType) {
this.reflectionType = reflectionType;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

}