package com.example.corona.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("username")
@Expose
private String username;
@SerializedName("password")
@Expose
private String password;
@SerializedName("fullname")
@Expose
private String fullname;
@SerializedName("email")
@Expose
private String email;
@SerializedName("valid")
@Expose
private Boolean valid;

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getFullname() {
return fullname;
}

public void setFullname(String fullname) {
this.fullname = fullname;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Boolean getValid() {
return valid;
}

public void setValid(Boolean valid) {
this.valid = valid;
}

}