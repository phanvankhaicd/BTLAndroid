package com.example.corona.Network;

public class DataServices {

    public static APIServicesOutsite getAPIServiceOutsite() {
        return RetrofitClient.getClientOutsite().create(APIServicesOutsite.class);
    }
    public static APIServices getAPIService() {
        return RetrofitClient.getClient().create(APIServices.class);
    }
}
