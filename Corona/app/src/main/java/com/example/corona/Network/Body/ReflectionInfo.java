package com.example.corona.Network.Body;

public class ReflectionInfo {
//      "questionInfo1": 1,
//              "questionInfo2": 1,
//              "questionInfo3": 1,
//              "description": "ahihi Do ngok",
//              "time": "121312313",
//              "addres": "ádasdas"
    int questionInfo1, questionInfo2, questionInfo3;
    String description, time, address;

    public ReflectionInfo(int questionInfo1, int questionInfo2, int questionInfo3, String description, String time, String address) {
        this.questionInfo1 = questionInfo1;
        this.questionInfo2 = questionInfo2;
        this.questionInfo3 = questionInfo3;
        this.description = description;
        this.time = time;
        this.address = address;
    }
}
