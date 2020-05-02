package com.example.corona.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitOutsite = null;
    private static Retrofit retrofit = null;
    private static String BASE_URL_OUTSITE = " https://corona-api.com/";
    //    private static String BASE_URL = "http://127.0.0.1:8082/";
    //May khac
    private static String BASE_URL = "http://10.0.3.2:8082/";
    //Genymotion

    public static Retrofit getClientOutsite() {
        if (retrofitOutsite == null) {
            retrofitOutsite = new Retrofit.Builder()
                    .baseUrl(BASE_URL_OUTSITE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitOutsite;
    }

    public static Retrofit getClient() {

        OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttp.addInterceptor(loggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp.build())
                    .build();
        }
        return retrofit;
    }
}
