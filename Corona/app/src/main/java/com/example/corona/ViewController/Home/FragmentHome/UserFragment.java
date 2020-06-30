package com.example.corona.ViewController.Home.FragmentHome;

import android.content.Context;
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

    LinearLayout lnLogout, lnUserinfo, lnFacebook, lnGmail;

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
                SharedPreferences preferences = getContext().getSharedPreferences("THE_SMILE", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                startActivity(new Intent(getContext(), LoginAcitivy.class));
                getActivity().finish();
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

    private void handleClick() {
        lnGmail.setOnClickListener(this);
        lnFacebook.setOnClickListener(this);
    }

    private void init(View view) {
        lnLogout = view.findViewById(R.id.ln_logout);
        lnUserinfo = view.findViewById(R.id.ln_userinfo);
        lnFacebook = view.findViewById(R.id.ln_fb);
        lnGmail = view.findViewById(R.id.ln_gmail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_fb:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/quantriungdungNcovi/"));
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
        }
    }
}
