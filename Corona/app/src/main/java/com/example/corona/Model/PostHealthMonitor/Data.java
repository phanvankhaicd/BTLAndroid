package com.example.corona.Model.PostHealthMonitor;

import com.example.corona.Model.HealthMonitor.ItemDeclare;
import com.example.corona.Model.HealthMonitor.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("ItemMonitor")
@Expose
private ItemDeclare itemMonitor;
@SerializedName("user")
@Expose
private User user;

public ItemDeclare getItemMonitor() {
return itemMonitor;
}

public void setItemMonitor(ItemDeclare itemMonitor) {
this.itemMonitor = itemMonitor;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

}