package com.example.corona.ViewController.Home.FragmentHome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.corona.R;
import com.example.corona.Util.AppConfig;
import com.example.corona.ViewController.Home.ChangePassActivity;
import com.example.corona.ViewController.Login.LoginAcitivy;
import com.example.corona.ViewController.UserInfo.UserInfoActivity;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    public UserFragment() {
        // Required empty public constructor
    }

    LinearLayout lnLogout, lnUserinfo, lnFacebook, lnGmail, lnChangePass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        init(view);
        handleClick();
        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogConfirmLogout();
            }
        });
        lnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UserInfoActivity.class));
            }
        });
        return view;
    }
    void logout(){
        SharedPreferences preferences = getContext().getSharedPreferences("THE_SMILE", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
        startActivity(new Intent(getContext(), LoginAcitivy.class));
        getActivity().finish();
    }
    void DialogConfirmLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Xác nhận đăng xuất");
        builder.setCancelable(true);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void handleClick() {
        lnGmail.setOnClickListener(this);
        lnFacebook.setOnClickListener(this);
        lnChangePass.setOnClickListener(this);

    }

    private void init(View view) {
        lnLogout = view.findViewById(R.id.ln_logout);
        lnUserinfo = view.findViewById(R.id.ln_userinfo);
        lnFacebook = view.findViewById(R.id.ln_fb);
        lnGmail = view.findViewById(R.id.ln_gmail);
        lnChangePass = view.findViewById(R.id.ln_change_pass);
        if(!AppConfig.getLoginWithSocial(getContext()).equals(""))
            lnChangePass.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ln_fb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/quantriungdungNcovi/"));
                startActivity(intent);
                break;
            case R.id.ln_gmail:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/html");
                final PackageManager pm = getActivity().getPackageManager();
                final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
                String className = null;
                for (final ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.equals("com.google.android.gm")) {
                        className = info.activityInfo.name;

                        if(className != null && !className.isEmpty()){
                            break;
                        }
                    }
                }
                emailIntent.setData(Uri.parse("mailto:pvkhaicd@gmail.com"));
                emailIntent.setClassName("com.google.android.gm", className);
                startActivity(emailIntent);
                break;
            case R.id.ln_change_pass:
                intent = new Intent(getActivity(), ChangePassActivity.class);
                startActivity(intent);
                break;
        }
    }
}
