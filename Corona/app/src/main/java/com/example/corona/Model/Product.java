package com.example.corona.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("product_id")
    @Expose
    public Integer productId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("images")
    @Expose
    public String images;
    @SerializedName("price")
    @Expose
    public Integer price;

}

