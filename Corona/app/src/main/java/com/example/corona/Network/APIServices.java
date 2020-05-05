package com.example.corona.Network;


import com.example.corona.Model.Global.CoronaGlobal;
import com.example.corona.Model.User;
import com.example.corona.Model.VN.CoronaVN;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIServices {

    @FormUrlEncoded
    @POST("login")
    @Headers("username")
    Call<User> login(@FieldMap Map<String,String> map);


}
