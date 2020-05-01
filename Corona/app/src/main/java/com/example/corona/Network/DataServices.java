package com.example.corona.Network;

public class DataServices {

    public static APIServices getAPIService() {
        return RetrofitClient.getClient().create(APIServices.class);
    }
}
