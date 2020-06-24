package com.example.corona.Network;


import com.example.corona.Model.Declare;
import com.example.corona.Model.Global.CoronaGlobal;
import com.example.corona.Model.HealthMonitor.HealthMonitor;
import com.example.corona.Model.PostHealthMonitor.SendHealthMonitor;
import com.example.corona.Model.Reflection;
import com.example.corona.Model.Token;
import com.example.corona.Model.User;
import com.example.corona.Model.UserInfo;
import com.example.corona.Model.VN.CoronaVN;
import com.example.corona.Network.Body.CreateDeclare;
import com.example.corona.Network.Body.ReflectionInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.corona.Util.AppConfig.getToken;


public interface APIServices {

    @POST("login")
    Call<com.example.corona.Model.Login.UserInfo> login(@Body User user);

    @GET("api/health-monitor")
//    @Headers("token: string")
    Call<HealthMonitor> callHistoryDeclare(@Query("page") String page, @Query("size") String size, @Header("authorization") String token );

    @POST("api/health-monitor/send")
    Call<SendHealthMonitor> createDeclare(@Body CreateDeclare create, @Header("authorization") String token);

    @POST("api/reflection/info")
    Call<Reflection> createReflectionInfo(@Body ReflectionInfo info, @Header("authorization") String token);

    @GET("api/user")
    Call<UserInfo> getUserInfo( @Header("authorization") String token);
}
