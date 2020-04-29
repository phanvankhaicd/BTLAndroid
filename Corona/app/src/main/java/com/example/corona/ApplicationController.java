package com.example.corona;

import android.app.Application;

//import com.orhanobut.logger.AndroidLogAdapter;
//import com.orhanobut.logger.Logger;

public class ApplicationController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
