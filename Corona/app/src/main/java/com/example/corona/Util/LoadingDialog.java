package com.example.corona.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.corona.R;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;

    public LoadingDialog(Activity activity){
        this.activity = activity;

    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog_loading,null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();

    }

    public void dismissLoadingDialog(){
        dialog.dismiss();
    }
}
