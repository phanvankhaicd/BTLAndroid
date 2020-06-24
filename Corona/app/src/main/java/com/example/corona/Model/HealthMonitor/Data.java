package com.example.corona.Model.HealthMonitor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Data {

@SerializedName("list")
@Expose
private java.util.List<ItemDeclare> itemDeclare = null;
@SerializedName("user")
@Expose
private User user;

public java.util.List<ItemDeclare> getItemDeclare() {
return itemDeclare;
}

public void setItemDeclare(java.util.List<ItemDeclare> itemDeclare) {
this.itemDeclare = itemDeclare;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

}