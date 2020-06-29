package com.example.corona.Network.Body;

public class ReflectionInfo {
//      "questionInfo1": 1,
//              "questionInfo2": 1,
//              "questionInfo3": 1,
//              "description": "ahihi Do ngok",
//              "time": "121312313",
//              "addres": "ádasdas"
    int reflectionInfoQuestion1, reflectionInfoQuestion2, reflectionInfoQuestion3;
    String reflectionInfoDescription, reflectionInfoTime, reflectionInfoAddress;

    public ReflectionInfo(int questionInfo1, int questionInfo2, int questionInfo3, String description, String time, String address) {
        this.reflectionInfoQuestion1 = questionInfo1;
        this.reflectionInfoQuestion2 = questionInfo2;
        this.reflectionInfoQuestion3 = questionInfo3;
        this.reflectionInfoDescription = description;
        this.reflectionInfoTime = time;
        this.reflectionInfoAddress = address;
    }
}
