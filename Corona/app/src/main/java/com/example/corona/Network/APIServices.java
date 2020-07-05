package com.example.corona.Network;


import com.example.corona.Model.ChangePass.ChangePassRespone;
import com.example.corona.Model.HealthMonitor.HealthMonitor;
import com.example.corona.Model.PostHealthMonitor.SendHealthMonitor;
import com.example.corona.Model.ReflectionRS.Reflection;
import com.example.corona.Model.Register.Register;
import com.example.corona.Model.TokenFirebase.DeviceTokenFireBase;
import com.example.corona.Model.Update.UpdateAccountRS;
import com.example.corona.Model.User;
import com.example.corona.Model.UserInfoRS.UserInfo;
import com.example.corona.Network.Body.ChangePass;
import com.example.corona.Network.Body.CreateDeclare;
import com.example.corona.Network.Body.DeviceToken;
import com.example.corona.Network.Body.ReflectionInfo;
import com.example.corona.Network.Body.Register.NewAccount;
import com.example.corona.Network.Body.SocialAccount;
import com.example.corona.Network.Body.UpdateAccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface APIServices {

    @POST("login")
    Call<com.example.corona.Model.Login.UserInfo> login(@Body User user);

    @POST("loginFacebook")
    Call<com.example.corona.Model.Login.UserInfo> loginFacebook(@Body SocialAccount accout);

    @POST("loginGoogle")
    Call<com.example.corona.Model.Login.UserInfo> loginGoogle(@Body SocialAccount accout);

    //updateTokenDevice
    @POST("updateTokenDevice")
    Call<DeviceTokenFireBase> updateFirebaseToken(@Body DeviceToken deviceToken, @Header("authorization") String token);

    @POST("api/account/change-password")
    Call<ChangePassRespone> changePass(@Body ChangePass pass, @Header("authorization") String token);
    //    register
    @POST("register")
    Call<Register> register(@Body NewAccount user);

    @POST("api/account/update")
    Call<UpdateAccountRS> updateAccount(@Body UpdateAccount user, @Header("authorization") String token);

    //history getmonitor
    @GET("api/health-monitor")
//    @Headers("token: string")
    Call<HealthMonitor> callHistoryDeclare(@Query("page") String page, @Query("size") String size, @Header("authorization") String token);

    @POST("api/health-monitor/send")
    Call<SendHealthMonitor> createDeclare(@Body CreateDeclare create, @Header("authorization") String token);

    @POST("api/reflection/info")
    Call<Reflection> createReflectionInfo(@Body ReflectionInfo info, @Header("authorization") String token);


    @GET("api/account/info")
    Call<UserInfo> getUser(@Header("authorization") String token);

    @POST("api/reflection/info")
    Call<Reflection> updateUserInfo(@Body ReflectionInfo info, @Header("authorization") String token);


}
