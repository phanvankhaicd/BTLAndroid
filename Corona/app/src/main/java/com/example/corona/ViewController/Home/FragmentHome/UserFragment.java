package com.example.corona.ViewController.Home.FragmentHome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.corona.R;
import com.example.corona.ViewController.Home.MainActivity;
import com.example.corona.ViewController.Login.LoginAcitivy;
import com.example.corona.ViewController.UserInfo.UserInfoScreen;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    public UserFragment() {
        // Required empty public constructor
    }
    LinearLayout lnLogout,lnUserinfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        init(view);

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
                startActivity(new Intent(getContext(), UserInfoScreen.class));
            }
        });
        return view;
    }

    private void init(View view) {
        lnLogout = view.findViewById(R.id.ln_logout);
        lnUserinfo = view.findViewById(R.id.ln_userinfo);

    }
}
