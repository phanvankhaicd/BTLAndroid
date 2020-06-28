package com.example.corona.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.DecimalFormat;

public class AppConfig {
    final static String SHARED_PREFERENCES_NAME = "THE_SMILE";
    static String tokenFireBase;

    public static void setToken(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", name);
        editor.apply();
    }
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
    public static String numberWithComas(String number){
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");

        return formatter.format(amount);
    }
    public static String timeConverse(String time) {
        String[] strTime = time.split("T");
        String[] date = strTime[0].split("-");

        return date[2] + "/" + date[1] + "/" + date[0] + " " + strTime[1].split("\\.")[0];
    }

    public static String handleTokenFirebase() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("AA", "getInstanceId failed", task.getException());
                            return;
                        }
                        tokenFireBase = task.getResult().getToken();
                    }
                });
        return tokenFireBase;
    }

}
