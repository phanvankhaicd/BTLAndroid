package com.example.corona.Network.Body;

public class ChangePass {

//    {
//        "oldPassword": "1234567",
//            "newPassword": "123456"
//    }
    String oldPassword, newPassword;

    public ChangePass() {
    }

    public ChangePass(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
