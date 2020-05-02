package com.example.corona.Network;



import com.example.corona.Model.Global.CoronaGlobal;
import com.example.corona.Model.VN.CoronaVN;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIServicesOutsite {
    @GET("countries/vn")
    Call<CoronaVN> getCoronaVN();
    @GET("timeline")
    Call<CoronaGlobal> getCoronaGlobal();
//    https://corona-api.com/timeline
//    @GET("/users/products")
//    Call<ListProductResult> getListProduct();
}
